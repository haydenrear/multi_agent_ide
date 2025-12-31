package com.hayden.multiagentide.model.acp

import com.agentclientprotocol.model.ContentBlock

fun extractText(content: ContentBlock): String? = when (content) {
    is ContentBlock.Text -> content.text
    is ContentBlock.ResourceLink -> content.title ?: content.name
    is ContentBlock.Resource -> content.resource.toString()
    is ContentBlock.Audio -> "[audio:${content.mimeType}]"
    is ContentBlock.Image -> "[image:${content.mimeType}]"
}
