//
//  ProgramEntryDetailView.swift
//  iosApp
//
//  Created by Anja on 04.09.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProgramEntryDetailView: View {
    @EnvironmentObject var viewModel: AppViewModel
    let entry: ProgramEntry
    
    var body: some View {
        let formatColor = Color(hexStringToUIColor(hex: entry.format.hexColor))
        
        ScrollView {
            VStack (alignment: .leading) {
                HStack {
                    Text(entry.format.label)
                        .foregroundColor(formatColor)
                        .font(.caption)
                    Spacer()
                    Image(systemName: viewModel.getFavStateIcon(entryId: entry.id))
                        .foregroundColor(.yellow)
                        .onTapGesture {
                            viewModel.toggleFav(programEntry: entry)
                        }
                }
                Text(entry.name).font(.title)
                HStack {
                    Image(systemName: "clock")
                    Text(timeFormatter(time: entry.timeRange.start) + " - " + timeFormatter(time: entry.timeRange.end) + " Uhr")
                        .font(.caption2)
                }
                .padding(.top)
                HStack {
                    Image(systemName: "location")
                    Text(entry.room.name)
                        .font(.caption2)
                }
                .padding(.bottom)
                Text(entry.description_)
                    .font(.callout)
                Divider()
                if entry.speakerWithRoles.contains { speaker in
                    speaker.role == Role.speaker
                } {
                    Text("Speaker")
                        .font(.title2)
                    SpeakerPreviewView(speakers: entry.speakerWithRoles.filter({ speaker in
                        speaker.role == Role.speaker
                    }))
                }
                if entry.speakerWithRoles.contains { speaker in
                    speaker.role == Role.moderator
                } {
                    Text("Moderation")
                        .font(.title2)
                    SpeakerPreviewView(speakers: entry.speakerWithRoles.filter({ speaker in
                        speaker.role == Role.moderator
                    }))
                }
            }
            .padding(.leading)
            .padding(.trailing)
        }
    }
}
