package com.apk.claw.android.ui.skills

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apk.claw.android.R
import com.apk.claw.android.base.BaseActivity
import com.apk.claw.android.tool.BaseTool
import com.apk.claw.android.tool.ToolRegistry
import com.apk.claw.android.widget.CommonToolbar

/**
 * 技能页面 - 展示所有可用的 AI 工具
 */
class SkillsActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skills)

        initToolbar()
        initRecyclerView()
    }

    private fun initToolbar() {
        findViewById<CommonToolbar>(R.id.toolbar).apply {
            setTitle(getString(R.string.skills_title))
            showBackButton(true) { finish() }
        }
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val tools = ToolRegistry.getAllTools()
        val adapter = SkillsAdapter(tools)
        recyclerView.adapter = adapter
    }
}

/**
 * 技能列表适配器
 */
class SkillsAdapter(private val tools: List<BaseTool>) : RecyclerView.Adapter<SkillsAdapter.ViewHolder>() {

    class ViewHolder(val itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val tvName: android.widget.TextView = itemView.findViewById(R.id.tvName)
        val tvDescription: android.widget.TextView = itemView.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ViewHolder {
        val view = android.view.LayoutInflater.from(parent.context)
            .inflate(R.layout.item_skill, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tool = tools[position]
        holder.tvName.text = tool.getDisplayName()
        holder.tvDescription.text = tool.getDescription()
    }

    override fun getItemCount(): Int = tools.size
}
