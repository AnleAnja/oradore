//
//  HelperFunctions.swift
//  iosApp
//
//  Created by Anja on 04.09.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import UIKit
import shared

func hexStringToUIColor (hex:String) -> UIColor {
    var cString:String = hex.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()
    
    if (cString.hasPrefix("#")) {
        cString.remove(at: cString.startIndex)
    }
    
    if ((cString.count) != 6) {
        return UIColor.gray
    }
    
    var rgbValue:UInt64 = 0
    Scanner(string: cString).scanHexInt64(&rgbValue)
    
    return UIColor(
        red: CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0,
        green: CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0,
        blue: CGFloat(rgbValue & 0x0000FF) / 255.0,
        alpha: CGFloat(1.0)
    )
}

func timeFormatter(time: Int64) -> String {
    let formatter = DateFormatter()
    let timeAsDate = Date(timeIntervalSince1970: Double(time)/1000.0)
    formatter.dateFormat = "HH:mm"
    return formatter.string(from: timeAsDate)
}

struct SpeakerWithRole {
    let speaker: Speaker
    let role: Role
}

extension ProgramEntry {
    var speakerWithRoles: [SpeakerWithRole] {
        speakers.map { SpeakerWithRole(speaker: $0.first!, role: $0.second!) }
    }
}
