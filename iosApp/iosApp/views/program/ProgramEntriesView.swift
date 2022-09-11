//
//  ProgramEntriesView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

extension GroupProgramEntries: Identifiable {
  public var id: Int64 { start }
}

extension ProgramEntry: Identifiable { }

struct ProgramEntriesView: View {
    @EnvironmentObject var viewModel: AppViewModel
    @State private var searchText = ""
    
    let title: String
    let programEntries: [GroupProgramEntries]
    
    private var searchResults: [GroupProgramEntries] {
        if searchText.isEmpty {
            return programEntries
        } else {
            return viewModel.filterProgramEntries { entry in
                entry.name.lowercased().contains(searchText.lowercased()) ||
                entry.room.name.lowercased().contains(searchText.lowercased()) ||
                entry.speakerWithRoles.contains { speakerWithRole in
                    let speaker = speakerWithRole.speaker
                    return speaker.firstName.lowercased().contains(searchText.lowercased()) ||
                    speaker.lastName.lowercased().contains(searchText.lowercased()) ||
                    speaker.company.lowercased().contains(searchText.lowercased()) ||
                    speaker.jobTitle.lowercased().contains(searchText.lowercased())
                } ||
                entry.format.label.lowercased().contains(searchText.lowercased())
            }
        }
    }
    
    var body: some View {
        NavigationView {
            List {
                ForEach(searchResults) { group in
                    let start = group.start
                    let entries = group.entries
                    Section(header: HStack {
                        VStack { Divider() }
                        Text(timeFormatter(time: start))
                            .font(.headline)
                        VStack { Divider() }
                    }){
                        ForEach(entries) { entry in
                            let formatColor = Color(hexStringToUIColor(hex: entry.format.hexColor))
                            NavigationLink(destination: ProgramEntryDetailView(entry: entry)) {
                                HStack {
                                    Divider()
                                        .frame(maxWidth:2)
                                        .background(formatColor)
                                    VStack (alignment: .leading) {
                                        ProgramEntryView(programEntry: entry, formatColor: formatColor)
                                    }
                                }
                            }
                        }
                    }
                }
                
            }
            .navigationTitle(title)
            .searchable(text: $searchText)
        }
    }
}

struct ProgramEntryView : View {
    let programEntry: ProgramEntry
    let formatColor: SwiftUI.Color
    @EnvironmentObject var viewModel: AppViewModel
    
    var body: some View {
        VStack (alignment: .leading) {
            HStack {
                Text(programEntry.format.label)
                    .foregroundColor(formatColor)
                    .font(.footnote.bold())
                Spacer()
                Image(systemName: viewModel.getFavStateIcon(entryId: programEntry.id))
                    .foregroundColor(.yellow)
            }
            Text(timeFormatter(time: programEntry.timeRange.start) + " - " + timeFormatter(time: programEntry.timeRange.end) + " Uhr" + " in \(programEntry.room.name)")
                .font(.footnote)
            Text(programEntry.name)
                .font(.title)
        }
        Spacer()
        SpeakerPreviewView(speakers: programEntry.speakerWithRoles)
    }
}

extension SpeakerWithRole: Identifiable {
    var id: String { speaker.id }
}

struct SpeakerPreviewView : View {
    let speakers: [SpeakerWithRole]
    
    var body: some View {
        ForEach(speakers) { speakerWithRole in
            HStack {
                SpeakerPreviewContent(speaker: speakerWithRole.speaker)
                Spacer()
                if let imgUrl = speakerWithRole.speaker.imgPreview {
                    SpeakerImageView(url: imgUrl)
                } else {
                    EmptyView()
                }
            }
        }
    }
}
