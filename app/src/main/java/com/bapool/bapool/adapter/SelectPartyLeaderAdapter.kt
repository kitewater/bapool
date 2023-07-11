package com.bapool.bapool.adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bapool.bapool.databinding.ChattingAndPartyUserItemBinding
import com.bapool.bapool.retrofit.data.FirebaseUserInfo

class SelectPartyLeaderAdapter(
    val context: Context,
    val partyUserInfo: ArrayList<Map<String, FirebaseUserInfo>>,
    val currentUserId: String,
)  : RecyclerView.Adapter<SelectPartyLeaderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ChattingAndPartyUserItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("asdfasdfasdf", partyUserInfo[position].keys.toString())
        if (!partyUserInfo[position].keys.toString().equals(currentUserId)) {
            holder.bindItems(partyUserInfo[position])


        }

    }


    override fun getItemCount(): Int {
        return partyUserInfo.size
    }

    inner class ViewHolder(private val binding: ChattingAndPartyUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val clickEvent = binding.userNameImageBackground
        fun bindItems(item: Map<String, FirebaseUserInfo>) {


            val userInfo = item.values.firstOrNull()

            if (userInfo != null) {
                binding.userId.text = userInfo.nickName
                //어플리케이션 내에서 받아오는중
                val imageName = "image${userInfo.imgUrl}"
                val resourceId =
                    context.resources.getIdentifier(imageName, "drawable", context.packageName)
                binding.userImage.setImageResource(resourceId)

                clickEvent.setOnClickListener{
                    confirmDialog(userInfo.nickName)
                }
            }
        }
    }


    fun confirmDialog(nickName: String) {

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setMessage("${nickName}sla을 그룹장으로 임명하시겠습니까?") // Set the dialog message
        alertDialogBuilder.setPositiveButton("확인") { dialog, _ ->
            Toast.makeText(context, "positive", Toast.LENGTH_SHORT).show()
            //그룹장 임명 api로 가기.
            //그룹장 임명 api 후 그룹장이 변경되었습니다 알람.
        }

        alertDialogBuilder.setNegativeButton("취소") { dialog, _ ->
            Toast.makeText(context, "negative", Toast.LENGTH_SHORT).show()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}
