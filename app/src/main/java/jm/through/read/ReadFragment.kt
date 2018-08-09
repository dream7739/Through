package jm.through.read

import android.app.Fragment
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import jm.through.R
import jm.through.R.id.read_progress
import jm.through.read.MailReader.readList

class ReadFragment : Fragment(), View.OnClickListener {

    //TODO 너무 많은 시간이 걸림(가장 큰 문제)
    //TODO 얼마만큼의 메일을 읽어올 것인지 모름(상의 후 수정)

    //TODO 위의 문제가 해결이 된다면
    //TODO 된다면 progressbar의 색을 변경하고 싶음
    //TODO Adapter와 Listener부분을 잘 이해하지 못하겠음

    //var readList = ArrayList<ReadData>()//Data를 넣을 ArrayList
    lateinit var rAdapter: ReadAdapter //recycler연결시킬 adapte
    lateinit var checkRecycler: RecyclerView
    lateinit var readProgress: ProgressBar
    @RequiresApi(Build.VERSION_CODES.M)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val checkView: View = inflater!!.inflate(R.layout.fragment_check, container, false)
        checkRecycler = checkView.findViewById(R.id.recycler) as RecyclerView
        readProgress = checkView.findViewById(read_progress) as ProgressBar

        var readTask = ReadTask()
        readTask.execute()

        return checkView
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(v: View?) {
        val idx: Int = checkRecycler.getChildAdapterPosition(v)
        Toast.makeText(context, idx.toString(), Toast.LENGTH_SHORT).show()
    }


    inner class ReadTask : AsyncTask<Void, Void, Void>() {
        override fun onProgressUpdate(vararg values: Void?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            readProgress.visibility = View.INVISIBLE
            try {
                rAdapter = ReadAdapter(readList)
                rAdapter.setOnItemClickListener(this@ReadFragment)
                checkRecycler.adapter = rAdapter
                checkRecycler.layoutManager = LinearLayoutManager(activity)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.v("fail", "")
            }
        }

        override fun doInBackground(vararg params: Void?): Void? {
            var reader = MailReader()
            reader.readMail("dream7739", "hjmh9811387")
            Log.v("list",readList.toString())
            return null
        }

        override fun onCancelled(result: Void?) {
            super.onCancelled(result)
        }

        override fun onCancelled() {
            super.onCancelled()
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }
    }


}