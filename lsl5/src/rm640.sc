;;; Sierra Script 1.0 - (do not remove this comment)
(script# 640)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Talker)
(use Osc)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm640 0
)

(local
	local0
	biazWaiting
	local2
	local3
	recordOnWall =  1
	local5
	turnTableWRecordCycleSpeed =  18
	local7
)
(procedure (localproc_2b30)
	(= biazWaiting 1)
	(TimePrint 640 48)
	(Say Reverse_Biaz 640 49 #dispose)
)

(instance rm640 of LLRoom
	(properties
		picture 640
		north 660
	)
	
	(method (init)
		(ego init: normalize: 570)
		(HandsOn)
		(switch prevRoomNum
			(north
				(ego posn: 189 86 setHeading: 180 edgeHit: 0)
				(= biazWaiting 1)
				(if (and (not (Btst fRecordOnPlayer)) (not (ego has: iGoldRecord)))
					(record init: approachVerbs: verbLook verbDo)
				)
				(if (Btst fRecordOnPlayer) (record2 init: approachVerbs: verbLook verbDo))
				(theMusic number: 620 setVol: 127 flags: mNOPAUSE play:)
			)
			(else 
				(ego
					posn: 59 139
					setHeading: 90
					edgeHit: 0
					setPri: 9
					setScript: sExitElevator
				)
				(record init: approachVerbs: verbLook verbDo)
			)
		)
		(LoadMany PICTURE 650)
		(LoadMany VIEW 650 641 1672)
		(LoadMany SOUND 640 641 642 643 644 645 646 201 648 621 622)
		(super init:)
		(stereo init: approachVerbs: verbLook verbDo verbUse)
		(elevator init: approachVerbs: verbDo)
		(door init: approachVerbs: verbDo)
		(reverseBiaz init: setScript: sMonitorRecording)
		(statue init:)
		(speakerA init:)
		(speakerB init:)
		(plant init:)
		(shelves init:)
		(recordJacketsA init:)
		(recordJacketsB init:)
		(fGoldRecords init:)
		(plaque init: approachVerbs: verbLook verbDo verbUse)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 117
						99 117
						105 126
						62 149
						77 149
						125 118
						145 112
						186 102
						229 111
						232 128
						206 141
						154 141
						130 139
						85 162
						51 159
						62 164
						54 175
						28 175
						16 170
						0 175
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						67 165
						105 171
						116 160
						212 160
						224 134
						255 129
						252 115
						193 100
						0 100
						0 93
						220 93
						287 107
						286 139
						236 148
						241 175
						231 181
						198 185
						142 185
						111 183
						90 184
						66 183
						61 174
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						103 83
						208 83
						201 78
						201 52
						178 52
						178 79
						0 79
						0 0
						319 0
						319 189
						245 189
						245 166
						290 160
						301 171
						317 161
						289 130
						315 130
						315 108
						279 78
						227 78
						228 83
						256 83
						262 86
						253 92
						226 92
						215 90
						100 90
					yourself:
				)
		)
	)
	
	(method (doit)
		(if (and (IsObjectOnControl ego cBLUE) (not biazWaiting))
			(localproc_2b30)
		)
		(if (and (IsObjectOnControl ego cGREEN) (not biazWaiting))
			(localproc_2b30)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbWalk
				(if (== curPic 650)
					(curRoom setScript: sReturnsFromStereo)
					1
				)
			)
			(verbLook
				(if (== curPic 650)
					(super doVerb: theVerb theItem &rest)
				else
					(TimePrint 640 0)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(cond 
			((not (== n 620))
				(theMusic fade: 0 15 12 1)
				(theMusic2 fade: 0 15 12 1)
			)
			(local3
				(theMusic setVol: 127 play:)
				(theMusic2 fade: 0 15 12 1)
			)
		)
	)
)

(instance sExitElevator of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 2))
			(1
				(elevatorDing play:)
				(= cycles 15)
			)
			(2
				(elevatorDoor play:)
				(elevator setCycle: EndLoop self)
			)
			(3
				(elevatorDoor stop:)
				(elevator setPri: 9)
				(ego setPri: 9 setMotion: MoveTo 73 148)
				(= seconds 2)
			)
			(4
				(elevatorDoor play:)
				(elevator setCycle: BegLoop self)
			)
			(5
				(elevatorDoor stop:)
				(ego setPri: -1)
				(elevator stopUpd: 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGetRecord of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: FALSE)
				(ego setHeading: 0 self)
			)
			(1
				(ego
					view: 641
					setLoop: 0
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(2
				(record dispose:)
				(ego get: 5 setCycle: BegLoop self)
			)
			(3
				(if recordOnWall
					(SolvePuzzle 12 fGetGoldRecord)
					(= recordOnWall FALSE)
					(TimePrint 640 1)
					(TimePrint 640 2)
					(TimePrint 640 3 #at -1 185)
				)
				(ego normalize: 570 loop: 3)
				(User canInput: TRUE)
				(self dispose:)
			)
		)
	)
)

(instance sMonitorRecording of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(if (Random 0 1)
					(reverseBiaz setLoop: 0 setCel: 0 setCycle: EndLoop self)
				else
					(reverseBiaz setLoop: 1 setCel: 0 setCycle: EndLoop self)
				)
			)
			(2
				(reverseBiaz setCycle: BegLoop self)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance sSpeed78Reverse of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (needle cel?) (= seconds 10) else (self dispose:))
			)
			(1
				(HandsOff)
				(if (not (DoSound SoundOn))
					(TimePrint 640 4 #at -1 20 #dispose self)
				else
					(theMusic2 setVol: 80)
					(message1 play: self)
				)
			)
			(2
				(theMusic2 setVol: 127)
				(SolvePuzzle 3 fPlayRecord78Reverse)
				(TimePrint 640 5 #at -1 20 #dispose self)
			)
			(3
				(TimePrint 640 6 #at -1 185 #dispose self)
			)
			(4
				(HandsOn)
				(User canControl: 0)
				(self dispose:)
			)
		)
	)
)

(instance sSpeed33Reverse of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (needle cel?) (= seconds 10) else (self dispose:))
			)
			(1
				(HandsOff)
				(if (not (DoSound SoundOn))
					(TimePrint 640 7 #at -1 20 #dispose self)
				else
					(theMusic2 setVol: 80)
					(message2 play: self)
				)
			)
			(2
				(theMusic2 setVol: 127)
				(SolvePuzzle 3 fPlayRecord33Reverse)
				(TimePrint 640 8 #at -1 20 #dispose self)
			)
			(3
				(TimePrint 640 9 #at -1 20 #dispose self)
			)
			(4
				(TimePrint 640 10 #at -1 20 #dispose self)
			)
			(5
				(HandsOn)
				(User canControl: 0)
				(self dispose:)
			)
		)
	)
)

(instance sSpeed78Forward of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (needle cel?) (= seconds 10) else (self dispose:))
			)
			(1
				(HandsOff)
				(if (not (DoSound SoundOn))
					(TimePrint 640 11 #at -1 20 #dispose self)
				else
					(theMusic2 setVol: 80)
					(message3 play: self)
				)
			)
			(2
				(theMusic2 setVol: 127)
				(SolvePuzzle 3 fPlayRecord78Forward)
				(TimePrint 640 12 #at -1 185 #dispose self)
			)
			(3
				(HandsOn)
				(User canControl: FALSE)
				(self dispose:)
			)
		)
	)
)

(instance sSpeed33Forward of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (needle cel?) (= seconds 10) else (self dispose:))
			)
			(1
				(HandsOff)
				(SolvePuzzle 3 fPlayRecord33Forward)
				(TimePrint 640 13 #at -1 20 #dispose self)
			)
			(2
				(HandsOn)
				(User canControl: FALSE)
				(self dispose:)
			)
		)
	)
)

(instance sUsesStereo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(User canInput: TRUE)
				(cast eachElementDo: #hide)
				(statue dispose:)
				(speakerA dispose:)
				(speakerB dispose:)
				(plant dispose:)
				(shelves dispose:)
				(recordJacketsA dispose:)
				(recordJacketsB dispose:)
				(fGoldRecords dispose:)
				(plaque dispose:)
				(reverseButton init: stopUpd:)
				(stopButton init: stopUpd:)
				(forwardButton init: stopUpd:)
				(button33 init: stopUpd:)
				(button78 init: stopUpd:)
				(needle init:)
				(if (Btst fRecordOnPlayer)
					(turnTableWRecord
						init:
						setCycle:
							(cond 
								((== local5 0) 0)
								((== local5 1) Forward)
								((== local5 2) Reverse)
							)
						cycleSpeed: turnTableWRecordCycleSpeed
					)
					(record2 dispose:)
				else
					(turntable init:)
				)
				(speakerOne
					init:
					setCycle: (if local7 MyRandCycle else 0)
				)
				(speakerTwo
					init:
					setCycle: (if local7 MyRandCycle else 0)
				)
				(curRoom drawPic: 650)
				(InFirstPerson 1)
				(theIconBar curIcon: (theIconBar at: ICON_DO))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(stereo dispose:)
				(theMusic fade: 0 15 12 0 self)
			)
			(1
				(theMusic pause: TRUE)
				(self dispose:)
			)
		)
	)
)

(instance sReturnsFromStereo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(if (curRoom script?) ((curRoom script?) dispose:))
				(if (not (needle cel?))
					(theMusic setVol: 127 pause: 0)
				)
				(stopButton dispose:)
				(forwardButton dispose:)
				(reverseButton dispose:)
				(button33 dispose:)
				(button78 dispose:)
				(needle dispose:)
				(if (Btst fRecordOnPlayer)
					(if (turnTableWRecord cycler?)
						(cond 
							(((turnTableWRecord cycler?) isKindOf: Forward) (= local5 1))
							(((turnTableWRecord cycler?) isKindOf: Reverse) (= local5 2))
						)
						(= local3 1)
					else
						(= local5 0)
						(= local3 0)
					)
					(record2 init:)
					(= turnTableWRecordCycleSpeed
						(turnTableWRecord cycleSpeed?)
					)
					(turnTableWRecord dispose:)
				else
					(turntable dispose:)
				)
				(if (speakerOne cycler?)
					(= local7 1)
				else
					(= local7 0)
				)
				(speakerOne dispose:)
				(speakerTwo dispose:)
				(if (not (ego has: iGoldRecord))
					(record show:)
				)
				(stereo init: approachVerbs: verbLook verbDo verbUse)
				(curRoom drawPic: (curRoom picture?))
				(elevator show:)
				(ego show:)
				(door show:)
				(reverseBiaz show:)
				(statue init:)
				(speakerA init:)
				(speakerB init:)
				(plant init:)
				(shelves init:)
				(recordJacketsA init:)
				(recordJacketsB init:)
				(fGoldRecords init:)
				(plaque init: approachVerbs: verbLook verbDo verbUse)
				(InFirstPerson 0)
				(User canControl: TRUE)
				(self dispose:)
			)
		)
	)
)

(instance sStopButtonPressed of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (needle cel?) (HandsOff))
				(stopButton startUpd:)
				(= cycles 1)
			)
			(1
				(recordSwitch play:)
				(stopButton setPri: 10 setCycle: Oscillate 1 self)
			)
			(2
				(cond 
					((not (turnTableWRecord cycler?))
						(TimePrint 640 14 #at -1 185)
					)
					((not (Btst fRecordOnPlayer))
						(TimePrint 640 15 #at -1 185)
					)
					((needle cel?)
						(needle doVerb: verbDo)
					)
				)
				(= cycles 1)
			)
			(3
				(User canControl: FALSE)
				(= seconds 3)
			)
			(4
				(turnTableWRecord setCycle: 0)
				(stopButton setPri: -1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sForwardButtonPressed of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(forwardButton startUpd:)
				(= cycles 1)
			)
			(1
				(recordSwitch play:)
				(forwardButton setPri: 10 setCycle: Oscillate 1 self)
			)
			(2
				(cond 
					((turnTableWRecord cycler?)
						(if
						(not ((turnTableWRecord cycler?) isKindOf: Forward))
							(turnTableWRecord setCycle: Forward)
							(if (needle cel?)
								(= local2 0)
								(speakerOne cue:)
							)
							(if (== (turnTableWRecord cycleSpeed?) 6)
								(curRoom setScript: sSpeed78Forward)
							else
								(curRoom setScript: sSpeed33Forward)
							)
						)
					)
					((Btst fRecordOnPlayer)
						(turnTableWRecord setCycle: Forward)
					)
					(else
						(TimePrint 640 16)
					)
				)
				(forwardButton setPri: -1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sReverseButtonPressed of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(reverseButton startUpd:)
				(= cycles 1)
			)
			(1
				(recordSwitch play:)
				(reverseButton setPri: 10 setCycle: Oscillate 1 self)
			)
			(2
				(cond 
					((turnTableWRecord cycler?)
						(if
						(not ((turnTableWRecord cycler?) isKindOf: Reverse))
							(turnTableWRecord setCycle: Reverse)
							(if (needle cel?) (= local2 0) (speakerOne cue:))
							(cond 
								((== (turnTableWRecord cycleSpeed?) 18) (curRoom setScript: sSpeed33Reverse))
								((== (turnTableWRecord cycleSpeed?) 6) (curRoom setScript: sSpeed78Reverse))
							)
						)
					)
					((Btst fRecordOnPlayer)
						(turnTableWRecord setCycle: Reverse)
					)
					(else
						(TimePrint 640 16)
					)
				)
				(reverseButton setPri: -1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sButton33Pressed of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(button33 startUpd:)
				(= cycles 1)
			)
			(1
				(recordSwitch play:)
				(button33 setPri: 10 setCycle: Oscillate 1 self)
			)
			(2
				(if (turnTableWRecord cycler?)
					(if (== (turnTableWRecord cycleSpeed?) 6)
						(turnTableWRecord cycleSpeed: 18)
						(if (needle cel?) (= local2 0) (speakerOne cue:))
						(cond 
							(((turnTableWRecord cycler?) isKindOf: Reverse)
								(curRoom setScript: sSpeed33Reverse)
							)
							(((turnTableWRecord cycler?) isKindOf: Forward)
								(curRoom setScript: sSpeed33Forward)
							)
						)
					)
				else
					(turnTableWRecord cycleSpeed: 18)
					(TimePrint 640 17)
					(TimePrint 640 18 #at -1 185)
				)
				(button33 setPri: -1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sButton78Pressed of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(button78 startUpd:)
				(= cycles 1)
			)
			(1
				(recordSwitch play:)
				(button78 setPri: 10 setCycle: Oscillate 1 self)
			)
			(2
				(if (turnTableWRecord cycler?)
					(if (== (turnTableWRecord cycleSpeed?) 18)
						(turnTableWRecord cycleSpeed: 6)
						(if (needle cel?)
							(= local2 0)
							(speakerOne cue:)
						)
						(if ((turnTableWRecord cycler?) isKindOf: Reverse)
							(curRoom setScript: sSpeed78Reverse)
						else
							(curRoom setScript: sSpeed78Forward)
						)
					)
				else
					(turnTableWRecord cycleSpeed: 6)
					(TimePrint 640 19)
					(TimePrint 640 18 #at -1 185)
				)
				(button78 setPri: -1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterElevator of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(elevatorDing play:)
				(= cycles 15)
			)
			(1
				(elevatorDoor play:)
				(elevator setCycle: EndLoop self)
			)
			(2
				(elevatorDoor stop:)
				(elevator setPri: 10)
				(ego setPri: 9 setMotion: MoveTo 59 139 self)
			)
			(3 (ego setHeading: 90 self))
			(4
				(Say Reverse_Biaz 640 20 #dispose #caller self)
			)
			(5
				(elevatorDoor play:)
				(elevator setCycle: BegLoop self)
			)
			(6
				(elevatorDoor stop:)
				(curRoom newRoom: 620)
				(self dispose:)
			)
		)
	)
)

(instance sPlayMusic of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local2
					(needleDownSpin play: self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOn)
				(User canControl: FALSE)
				(cond 
					(((turnTableWRecord cycler?) isKindOf: Forward)
						(if (== (turnTableWRecord cycleSpeed?) 6)
							(theMusic2 number: 641 flags: mNOPAUSE setLoop: -1 play:)
							(curRoom setScript: sSpeed78Forward)
						else
							(theMusic2 number: 640 flags: mNOPAUSE setLoop: -1 play:)
							(curRoom setScript: sSpeed33Forward)
						)
					)
					(((turnTableWRecord cycler?) isKindOf: Reverse)
						(cond 
							((== (turnTableWRecord cycleSpeed?) 18)
								(theMusic2 number: 642 flags: mNOPAUSE setLoop: -1 play:)
								(curRoom setScript: sSpeed33Reverse)
							)
							((== (turnTableWRecord cycleSpeed?) 6)
								(theMusic2 number: 643 flags: mNOPAUSE setLoop: -1 play:)
								(curRoom setScript: sSpeed78Reverse)
							)
						)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance stereo of Feature
	(properties
		x 174
		y 163
		z 3
		nsTop 154
		nsLeft 151
		nsBottom 167
		nsRight 197
		description {the stereo}
		sightAngle 40
		approachX 172
		approachY 185
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(curRoom setScript: sUsesStereo)
			)
			(verbDo
				(curRoom setScript: sUsesStereo)
			)
			(verbUse
				(if (== theItem iGoldRecord)
					(Bset fRecordOnPlayer)
					(ego put: iGoldRecord)
					(curRoom setScript: sUsesStereo)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plaque of Feature
	(properties
		x 167
		y 161
		z 29
		nsTop 119
		nsLeft 153
		nsBottom 145
		nsRight 181
		description {the plaque}
		sightAngle 40
		approachX 159
		approachY 183
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (cast contains: record)
					(TimePrint 640 21)
				else
					(TimePrint 640 22)
				)
			)
			(verbUse
				(switch theItem
					(iGoldRecord
						(record init:)
						(ego put: iGoldRecord)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance record of View
	(properties
		x 169
		y 162
		z 33
		description {the gold record}
		sightAngle 40
		approachX 159
		approachY 183
		view 640
		loop 2
		priority 14
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(ego setScript: sGetRecord)
			)
			(verbLook
				(TimePrint 640 23)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance record2 of View
	(properties
		x 154
		y 164
		z 16
		description {the stereo}
		sightAngle 40
		approachX 159
		approachY 183
		view 640
		loop 3
		priority 14
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(curRoom setScript: sUsesStereo)
			)
			(verbDo
				(curRoom setScript: sUsesStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance elevator of Prop
	(properties
		x 50
		y 77
		description {the elevator}
		approachX 65
		approachY 152
		lookStr {This is the elevator in which you arrived.}
		view 640
		priority 10
		signal (| ignrAct fixPriOn stopUpdOn)
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(cond 
					((Btst fBlewGig)
						(curRoom setScript: sEnterElevator)
					)
					(biazWaiting
						(Bset fBlewGig)
						(Say Reverse_Biaz 640 24 #dispose)
					)
					(else (localproc_2b30))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance reverseBiaz of Prop
	(properties
		x 251
		y 56
		description {Reverse Biaz}
		view 642
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 640 25)
				(if (not biazWaiting) (localproc_2b30))
			)
			(verbTalk
				(TimePrint 640 26)
				(if (not biazWaiting) (localproc_2b30))
			)
			(verbDo
				(TimePrint 640 27)
			)
			(verbUse
				(switch theItem
					(iChampagne
						(TimePrint 640 28)
					)
					(else
						(TimePrint 640 29)
					)
				)
			)
			(verbZipper
				(TimePrint 640 30)
			)
			(else
				(TimePrint 640 31)
			)
		)
	)
)

(instance turntable of Feature
	(properties
		x 174
		y 160
		nsTop 109
		nsLeft 112
		nsBottom 132
		nsRight 191
		description {the turntable}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 640 32)
			)
			(verbDo
				(TimePrint 640 33)
			)
			(verbUse
				(switch theItem
					(iGoldRecord
						(Bset fRecordOnPlayer)
						(ego put: iGoldRecord)
						(turnTableWRecord init:)
						(theIconBar curIcon: (theIconBar at: ICON_DO))
						(theGame setCursor: ((theIconBar curIcon?) cursor?))
						(self dispose:)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance turnTableWRecord of Prop
	(properties
		x 150
		y 133
		description {the gold record}
		view 650
		loop 10
		cycleSpeed 18
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (self cycler?)
					(TimePrint 640 34)
				else
					(TimePrint 640 35)
				)
			)
			(verbDo
				(if (self cycler?)
					(TimePrint 640 36)
				else
					(Bclr fRecordOnPlayer)
					(ego get: iGoldRecord)
					(turntable init:)
					(self dispose:)
				)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance needle of Prop
	(properties
		x 191
		y 111
		description {the tone arm}
		view 650
		loop 6
		priority 11
		signal (| fixPriOn stopUpdOn)
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (self cel?)
					(TimePrint 640 37)
				else
					(TimePrint 640 38)
				)
			)
			(verbDo
				(cond 
					((self cel?)
						(= y (- y 1000))
						(= z 0)
						(HandsOff)
						(self setCycle: BegLoop speakerOne)
						(cond 
							((== (curRoom script?) sStopButtonPressed)
								((curRoom script?) cue:)
							)
							((curRoom script?)
								((curRoom script?) dispose:)
							)
						)
					)
					((Btst fRecordOnPlayer)
						(if
							(and
								(turnTableWRecord cycler?)
								(not (curRoom script?))
							)
							(= y (+ y 1000))
							(= z 1000)
							(= local2 1)
							(HandsOff)
							(self setCycle: EndLoop speakerOne)
						else
							(TimePrint 640 39)
						)
					)
					(else
						(TimePrint 640 40)
					)
				)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance speakerOne of Prop
	(properties
		x 243
		y 114
		description {the speakers}
		view 650
		loop 8
		cycleSpeed 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 640 41)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(needle stopUpd:)
		(if (needle cel?)
			(self setCycle: MyRandCycle)
			(speakerTwo setCycle: MyRandCycle)
			(self setScript: sPlayMusic)
		else
			(HandsOn)
			(User canControl: FALSE)
			(theMusic2 stop:)
			(self setCycle: 0)
			(speakerTwo setCycle: 0)
		)
	)
)

(instance speakerTwo of Prop
	(properties
		x 75
		y 117
		description {the speakers}
		view 650
		loop 9
		cycleSpeed 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 640 41)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(class MyRandCycle of Cycle

	(method (doit)
		(++ cycleCnt)
		(if (> cycleCnt (client cycleSpeed?))
			(client setCel: (Random 0 1))
			(= cycleCnt 0)
		)
	)
)

(instance door of Door
	(properties
		x 177
		y 21
		description {the studio door}
		approachX 195
		approachY 83
		lookStr {A small plaque on the door reads, "Studio A."}
		view 640
		loop 1
		signal stopUpdOn
		entranceTo 660
		moveToX 195
		moveToY 73
		enterType 0
		exitType 0
	)
)

(instance reverseButton of Prop
	(properties
		x 105
		y 159
		description {the Reverse button}
		sightAngle 40
		view 650
		signal stopUpdOn
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sReverseButtonPressed)
			)
			(verbLook
				(TimePrint 640 42)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance stopButton of Prop
	(properties
		x 120
		y 160
		description {the Stop button}
		sightAngle 40
		view 650
		loop 1
		signal stopUpdOn
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sStopButtonPressed)
			)
			(verbLook
				(TimePrint 640 43)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance forwardButton of Prop
	(properties
		x 134
		y 161
		description {the Forward button}
		sightAngle 40
		view 650
		loop 2
		signal stopUpdOn
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sForwardButtonPressed)
			)
			(verbLook
				(TimePrint 640 44)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance button33 of Prop
	(properties
		x 188
		y 161
		description {the 33 button}
		sightAngle 40
		view 650
		loop 3
		signal stopUpdOn
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sButton33Pressed)
			)
			(verbLook
				(TimePrint 640 45)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance button78 of Prop
	(properties
		x 217
		y 159
		description {the 78 button}
		sightAngle 40
		view 650
		loop 5
		signal stopUpdOn
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sButton78Pressed)
			)
			(verbLook
				(TimePrint 640 46)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromStereo)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance statue of Feature
	(properties
		x 31
		y 141
		nsTop 114
		nsLeft 24
		nsBottom 169
		nsRight 43
		description {the statue}
		sightAngle 40
		lookStr {This sculpture represents the patron saint of recording companies--\nSt. Needle.}
	)
)

(instance speakerA of Feature
	(properties
		x 121
		y 200
		z 50
		nsTop 137
		nsLeft 112
		nsBottom 164
		nsRight 131
		description {the left speaker}
		sightAngle 40
		lookStr {This transducer converts electrical energy into rapidly pulsating sound waves.}
	)
)

(instance speakerB of Feature
	(properties
		x 215
		y 199
		z 50
		nsTop 137
		nsLeft 205
		nsBottom 161
		nsRight 225
		description {the right speaker}
		sightAngle 40
		lookStr {This transducer converts electrical energy into rapidly pulsating sound waves.}
	)
)

(instance plant of Feature
	(properties
		x 233
		y 71
		nsTop 45
		nsLeft 220
		nsBottom 93
		nsRight 246
		description {the plant}
		sightAngle 40
		lookStr {Upon close examination, you determine this is a plant.}
	)
)

(instance shelves of Feature
	(properties
		x 268
		y 110
		nsTop 83
		nsLeft 257
		nsBottom 137
		nsRight 279
		description {the shelves}
		sightAngle 40
		lookStr {The office's shelves are filled with records, tapes, and books.}
	)
)

(instance recordJacketsA of Feature
	(properties
		x 133
		y 15
		nsTop 10
		nsLeft 104
		nsBottom 20
		nsRight 162
		description {the record jackets}
		sightAngle 40
		lookStr {Many famous record album jackets adorn the walls of the office of des Rever Records.}
	)
)

(instance recordJacketsB of Feature
	(properties
		x 268
		y 15
		nsTop 11
		nsLeft 222
		nsBottom 20
		nsRight 315
		description {the record jackets}
		sightAngle 40
		lookStr {Many famous record album jackets adorn the walls of the office of des Rever Records.}
	)
)

(instance elevatorDing of Sound
	(properties
		number 621
	)
)

(instance elevatorDoor of Sound
	(properties
		number 622
	)
)

(instance message1 of Sound
	(properties
		number 644
	)
)

(instance message2 of Sound
	(properties
		number 645
	)
)

(instance message3 of Sound
	(properties
		number 646
	)
)

(instance recordSwitch of Sound
	(properties
		number 201
	)
)

(instance needleDownSpin of Sound
	(properties
		number 648
	)
)

(instance Reverse_Biaz of Talker
	(properties
		nsTop 15
		nsLeft 35
		view 1672
		loop 3
		viewInPrint 1
		name "Reverse Biaz"
	)
	
	(method (init)
		(= bust reverseBust)
		(= eyes reverseEyes)
		(= mouth reverseMouth)
		(super init: &rest)
	)
)

(instance reverseBust of Prop
	(properties
		view 1672
		loop 1
	)
)

(instance reverseEyes of Prop
	(properties
		nsTop 31
		nsLeft 22
		view 1672
		loop 2
		cycleSpeed 70
	)
)

(instance reverseMouth of Prop
	(properties
		nsTop 36
		nsLeft 15
		view 1672
		cycleSpeed 5
	)
)

(instance fGoldRecords of Feature
	(properties
		x 134
		y 77
		z 37
		nsTop 34
		nsLeft 108
		nsBottom 47
		nsRight 160
		description {the gold records}
		sightAngle 40
		lookStr {These gold records are firmly attached to the wall.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 640 47)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
