;;; Sierra Script 1.0 - (do not remove this comment)
(script# 660)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Timer)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm660 0
)

(local
	local0
	numTakes =  1
	local2
	local3
	local4
	talkCount
	local6
	local7
	local8
	local9
	[local10 18] = [0 2 4 5 7 9 11 12 14 16 17 19 21 23 24 26 28 29]
)
(procedure (localproc_2850 param1)
	(keyboard
		at:
			(switch (param1 message?)
				(97 30)
				(119 29)
				(115 28)
				(101 27)
				(100 26)
				(102 25)
				(116 24)
				(103 23)
				(121 22)
				(104 21)
				(117 20)
				(106 19)
				(107 18)
				(111 17)
				(108 16)
				(112 15)
				(59 14)
				(39 13)
				(93 12)
				(65 18)
				(87 17)
				(83 16)
				(69 15)
				(68 14)
				(70 13)
				(84 12)
				(71 11)
				(89 10)
				(72 9)
				(85 8)
				(74 7)
				(75 6)
				(79 5)
				(76 4)
				(80 3)
				(58 2)
				(34 1)
				(125 0)
				(else  -1)
			)
	)
)

(procedure (localproc_2a2d param1 &tmp temp0 temp1)
	(= temp0 (- 30 [local10 (/ (param1 x?) 18)]))
	(return
		(cond 
			((> (param1 y?) 180) (return (keyboard at: temp0)))
			(
				(and
					(< temp0 30)
					(not ((= temp1 (keyboard at: (+ temp0 1))) white?))
					(temp1 onMe: param1)
				)
				(return temp1)
			)
			(
				(and
					temp0
					(not ((= temp1 (keyboard at: (- temp0 1))) white?))
					(temp1 onMe: param1)
				)
				(return temp1)
			)
			((> (param1 y?) 164) (return (keyboard at: temp0)))
			(else (return 0))
		)
	)
)

(instance rm660 of LLRoom
	(properties
		picture 660
		south 640
	)
	
	(method (init)
		(ego init: normalize: 570)
		(HandsOn)
		(switch prevRoomNum
			(south
				(ego posn: 292 250)
				(if (Btst 19)
					(curRoom setScript: sWalkIn)
				else
					(curRoom setScript: sEntersRoom)
				)
			)
			(else 
				(ego posn: 160 160 edgeHit: 0)
			)
		)
		(LoadMany PICTURE 670 1)
		(LoadMany VIEW 670 672 673 681 682 683 1672)
		(LoadMany SOUND 660 661 662 663 664 665)
		(super init:)
		(door init:)
		(reverseBiaz init:)
		(synth init: approachVerbs: 3)
		(musicStands init:)
		(recordingBooth init:)
		(overhangingMike init:)
		(drums init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						301 189
						301 187
						314 187
						314 170
						292 155
						301 144
						290 143
						269 144
						244 138
						237 137
						227 117
						233 104
						248 104
						250 99
						230 93
						196 108
						132 93
						123 83
						81 83
						3 154
						3 176
						30 176
						52 166
						86 175
						190 187
						205 187
						247 172
						274 187
						274 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						77 107
						103 106
						112 112
						154 112
						154 119
						114 122
						113 138
						65 139
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						154 140
						167 131
						223 130
						233 142
						294 165
						268 177
						218 158
						206 161
						191 159
						180 164
						165 156
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						159 116
						196 116
						188 130
						149 130
					yourself:
				)
		)
	)
	
	(method (dispose)
		(if (not (keyboard isEmpty:))
			(keyboard dispose:)
		)
		(theMusic2 fade: 0 15 12 1)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (== curPic 670)
					(if local2 (TimePrint 660 0) else (TimePrint 660 1))
				else
					(TimePrint 660 2)
				)
			)
			(verbDo
				(if (and (== curPic 670) (not local2))
					(TimePrint 660 3)
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

(instance sEntersRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fEnteredStudio)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo 292 182 self)
			)
			(2 (= seconds 2))
			(3
				(TimePrint 660 4)
				(TimePrint 660 5)
				(= seconds 2)
			)
			(4
				(Say Reverse_Biaz 660 6 #dispose #caller self)
			)
			(5 (HandsOn) (= seconds 3))
			(6
				(HandsOff)
				(Say Reverse_Biaz 660 7 #dispose #caller self)
			)
			(7
				(HandsOn)
				(ego setLoop: -1)
				(reverseBiaz setCel: 0 setCycle: EndLoop self)
			)
			(8
				(reverseBiaz setCel: 0)
				(ego setScript: sReverseReminds)
				(self dispose:)
			)
		)
	)
)

(instance sWalkIn of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 1))
			(1
				(ego setMotion: MoveTo 292 182 self)
			)
			(2
				(HandsOn)
				(ego setScript: sReverseReminds)
				(self dispose:)
			)
		)
	)
)

(instance sReverseReminds of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 30))
			(1
				(= state (- state 2))
				(Say Reverse_Biaz 660 8 #dispose #caller self)
			)
			(2 (self dispose:))
		)
	)
)

(instance sUsesSynth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 90)
				(= seconds 2)
			)
			(1
				(cast eachElementDo: #hide)
				(curRoom drawPic: 670)
				(theIconBar disable: ICON_WALK)
				(reverseBiaz
					view: 672
					setLoop: 0
					setCel: 0
					x: 203
					y: 72
					setPri: 2
					show:
				)
				(reelOne init:)
				(reelTwo init:)
				(reelThree init:)
				(reelFour init:)
				(vuMeter init:)
				(keyboard init:)
				(music init:)
				(synthPanel init:)
				(panelExtender init:)
				(controlPanel init:)
				(controlPanel2 init:)
				(synth dispose:)
				(musicStands dispose:)
				(recordingBooth dispose:)
				(overhangingMike dispose:)
				(drums dispose:)
				(= cycles 2)
			)
			(2
				(ego setScript: sSession)
				(self dispose:)
			)
		)
	)
)

(instance sReturnFromSynth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: (curRoom picture?))
				(cast eachElementDo: #show)
				(reverseBiaz hide:)
				(vuMeter hide:)
				(reelOne hide:)
				(reelTwo hide:)
				(reelThree hide:)
				(reelFour hide:)
				(keyboard dispose:)
				(music dispose:)
				(synthPanel dispose:)
				(panelExtender hide:)
				(door show:)
				(ego normalize: 570)
				(curRoom setScript: sEnterBooth)
				(self dispose:)
			)
		)
	)
)

(instance sSession of Script
	
	(method (changeState newState &tmp [str 20])
		(switch (= state newState)
			(0
				(HandsOff)
				(SetFFRoom 1000 reverseBiaz)
				(= seconds 2)
			)
			(1
				(switch local0
					(0
						(Say Reverse_Biaz 660 9 #dispose #caller self)
					)
					(1
						(if local9
							(= local9 0)
							(Say Reverse_Biaz 660 10 #dispose #caller self)
						else
							(-- local0)
							(= local9 0)
							(Say Reverse_Biaz 660 11 #dispose #caller self)
						)
					)
					(else 
						(if (< local9 10)
							(-- local0)
							(= local9 0)
							(Say Reverse_Biaz 660 12 #dispose #caller self)
						else
							(Say Reverse_Biaz 660 13 #dispose #caller self)
						)
					)
				)
			)
			(2
				(if (and (== numTakes 1) (not (HaveMouse)))
					(TimePrint 660 14
						#at -1 15
						#width 280
						#title {A Tip from AL}
					)
				)
				(if (> numTakes 1)
					(rewind play: self)
					(reelOne cycleSpeed: 5 setCycle: Reverse)
					(reelTwo cycleSpeed: 5 setCycle: Forward)
					(reelThree cycleSpeed: 5 setCycle: Reverse)
					(reelFour cycleSpeed: 5 setCycle: Forward)
				else
					(self cue:)
				)
			)
			(3
				(if (> numTakes 1)
					(musicStop play: self)
					(reelOne cycleSpeed: 10)
					(reelTwo cycleSpeed: 10)
					(reelThree cycleSpeed: 10)
					(reelFour cycleSpeed: 10)
				else
					(self cue:)
				)
			)
			(4
				(if (> numTakes 1)
					(reelOne setCycle: 0)
					(reelTwo setCycle: 0)
					(reelThree setCycle: 0)
					(reelFour setCycle: 0)
					(= seconds 2)
				else
					(self cue:)
				)
			)
			(5
				(Format @str 660 15 numTakes)
				(Say Reverse_Biaz @str #dispose #caller self)
			)
			(6
				(reverseBiaz setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(7
				(reverseBiaz setCycle: BegLoop self)
			)
			(8
				(reverseBiaz setLoop: 0 setCycle: EndLoop self)
			)
			(9
				(reverseBiaz setCycle: BegLoop self)
			)
			(10
				(reverseBiaz setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(11
				(reverseBiaz setCycle: BegLoop self)
			)
			(12
				(reverseBiaz setLoop: 0 setCel: 0)
				(= cycles 1)
			)
			(13
				(reelOne cycleSpeed: 17 setCycle: Forward)
				(reelTwo cycleSpeed: 15 setCycle: Reverse)
				(reelThree cycleSpeed: 17 setCycle: Forward)
				(reelFour cycleSpeed: 15 setCycle: Reverse)
				(vuMeter view: 670 setLoop: 1 setCel: 0 setCycle: Forward)
				(if (<= local0 1)
					(mouseDownHandler addToFront: keyboard)
					(keyDownHandler addToFront: keyboard)
					(DoSound Suspend 1)
					(theMusic
						number: 660
						flags: 1
						setLoop: 1
						play:
						send: 9 78 1
					)
					(DoSound Suspend 0)
				else
					(DoSound Suspend 1)
					(theMusic
						number: 660
						flags: 1
						setLoop: 1
						play: sAutoKeys
						send: 8 78 1
						send: 9 78 0
					)
					(DoSound Suspend 0)
				)
				(reverseBiaz setScript: sMonitorRecording)
				(HandsOn)
				(User canControl: 0)
				(theIconBar disable: 0 3 4 6 7)
				(if (> local0 1)
					(curRoom setScript: sAutoKeys)
					(self dispose:)
				)
				(self cue:)
			)
			(14 (StartTimer 50 2 self))
			(15
				(HandsOff)
				(if local7 (local7 release:))
				(= local7 0)
				(= local8 0)
				(mouseDownHandler delete: keyboard)
				(keyDownHandler delete: keyboard)
				(= seconds 3)
			)
			(16
				(HandsOn)
				(theIconBar disable: 0)
				(sMonitorRecording dispose:)
			)
			(17 (self dispose:))
		)
	)
)

(instance sMonitorRecording of Script
	(properties)
	
	(method (dispose)
		(reelOne setCycle: 0)
		(reelTwo setCycle: 0)
		(reelThree setCycle: 0)
		(reelFour setCycle: 0)
		(vuMeter setCycle: 0 setLoop: 0)
		(theMusic stop:)
		(reverseBiaz setLoop: 0 setCycle: BegLoop self)
		(if (> local0 1)
			(curRoom setScript: sEndSession)
		else
			(++ local0)
			(++ numTakes)
			(ego setScript: sSession)
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 6)))
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

(instance sAutoKeys of Script
	(properties)
	
	(method (changeState newState &tmp temp0 theMusicPrevSignal)
		(switch (= state newState)
			(0
				(User canInput: 0)
				(theIconBar disable: ICON_WALK ICON_LOOK ICON_TALK ICON_ZIPPER ICON_ITEM ICON_INVENTORY)
				(theIconBar curIcon: (theIconBar at: ICON_DO))
			)
			(1
				(if
				(== (= theMusicPrevSignal (theMusic prevSignal?)) -1)
					(register release:)
					(= cycles 1)
				else
					(= temp0 (- 30 (- theMusicPrevSignal 60)))
					(if register (register release:))
					(= register (keyboard at: temp0))
					(theGame
						setCursor:
							theCursor
							1
							(if (register white?)
								(+ (register x?) 5)
							else
								(register x?)
							)
							(if (register white?)
								(- (register y?) 10)
							else
								(- (register y?) 12)
							)
					)
					(register depress: 0)
					(self changeState: 0)
				)
			)
			(2
				(sMonitorRecording dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sEndSession of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (SetFFRoom 0) (= seconds 3))
			(1
				(= local2 1)
				(if local3
					(Say Reverse_Biaz 660 16 #dispose #caller self)
				else
					(SolvePuzzle 8 fAfterRecordingSession)
					(Say Reverse_Biaz 660 17 #dispose #caller self)
				)
			)
			(2
				(curRoom setScript: sReturnFromSynth)
				(self dispose:)
			)
		)
	)
)

(instance sEnterBooth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(User canControl: 0)
				(User canInput: 0)
				(ego setPri: 4 setMotion: PolyPath 229 101 self)
			)
			(1
				(theMusic number: 40 setVol: 127 setLoop: 1 play:)
				(door ignoreActors: 1 setPri: 3 setCycle: EndLoop self)
			)
			(2
				(ego setMotion: MoveTo 279 74 self)
			)
			(3
				(door dispose:)
				(curRoom drawPic: 670)
				(reverseBiaz show:)
				(ego view: 681 setLoop: 1 setCel: 0 x: 241 y: 71 show:)
				(reelOne show:)
				(reelTwo show:)
				(reelThree show:)
				(reelFour show:)
				(vuMeter show:)
				(keyboard init:)
				(music init:)
				(synthPanel init:)
				(panelExtender show:)
				(theIconBar disable: 0)
				(= seconds 3)
			)
			(4
				(Say Reverse_Biaz 660 18 #dispose #caller self)
			)
			(5
				(reverseBiaz setCycle: EndLoop self)
			)
			(6
				(reverseBiaz setCycle: BegLoop self)
			)
			(7
				(reverseBiaz setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(8
				(reverseBiaz setCycle: BegLoop self)
			)
			(9
				(reverseBiaz setLoop: 0)
				(= cycles 1)
			)
			(10
				(reverseBiaz view: 681 setLoop: 0 setCel: 0 x: 203 y: 71)
				(= cycles 1)
			)
			(11
				(reelOne setCycle: Forward)
				(reelTwo setCycle: Reverse)
				(self cue:)
			)
			(12
				(DoSound Suspend 1)
				(theMusic
					number: 660
					flags: 1
					setLoop: 1
					play: sStopMusic
					send: 8 78 1
				)
				(DoSound Suspend 0)
				(reverseBiaz setScript: sStopMusic)
				(self cue:)
			)
			(13 (= seconds 10))
			(14
				(theMusic fade: 80 15 12 0)
				(Say Reverse_Biaz 660 19 #dispose)
				(HandsOn)
				(User canControl: FALSE)
				(User canInput: TRUE)
				(theIconBar disable: ICON_WALK)
				(StartTimer 300 0 self)
			)
			(15
				(HandsOff)
				(Say Reverse_Biaz 660 20 #dispose)
				(keyboard dispose:)
				(curRoom drawPic: 1 6)
				(= ticks 120)
			)
			(16
				(TimePrint 660 21)
				(= ticks 120)
			)
			(17
				(curRoom newRoom: 200 IRISOUT)
				(self dispose:)
			)
		)
	)
)

(instance sStopMusic of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(if (== (theMusic prevSignal?) -1)
					(reelOne setCycle: 0)
					(reelTwo setCycle: 0)
					(= cycles 1)
				else
					(self changeState: 0)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sDrunkReverse of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(globalTimer dispose: delete:)
				(sEnterBooth dispose:)
				(= ticks 123)
			)
			(1
				(Say ego 660 22)
				(= ticks 60)
			)
			(2
				(Say Reverse_Biaz 660 23 #dispose #caller self)
			)
			(3 (= ticks 60))
			(4
				(Say Reverse_Biaz 660 24 #dispose #caller self)
			)
			(5 (= ticks 60))
			(6
				(ego hide:)
				(reverseBiaz
					view: 683
					setLoop: 0
					setCel: 0
					x: 231
					y: 71
					cycleSpeed: 13
					setCycle: CycleTo 1 1 self
				)
			)
			(7 (= ticks 60))
			(8
				(reverseBiaz setCycle: CycleTo 3 1 self)
			)
			(9 (= ticks 60))
			(10
				(reverseBiaz setCycle: EndLoop self)
			)
			(11
				(glassesClink play:)
				(= ticks 123)
			)
			(12
				(reverseBiaz setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(13 (= ticks 123))
			(14
				(Say Reverse_Biaz 660 25 #dispose #caller self)
			)
			(15 (= ticks 60))
			(16
				(Say ego 660 26)
				(= ticks 123)
			)
			(17
				(Say ego 660 27)
				(reverseBiaz setLoop: 0 setCel: 2 setCycle: CycleTo 3 1 self)
			)
			(18 (= ticks 60))
			(19
				(reverseBiaz setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(20 (= ticks 60))
			(21
				(switch talkCount
					(0 (Say ego 660 28))
					(1 (Say ego 660 29))
					(2 (Say ego 660 30))
					(3 (Say ego 660 31))
				)
				(= ticks 90)
			)
			(22
				(reverseBiaz setLoop: 0 setCel: 2 setCycle: CycleTo 3 1 self)
			)
			(23 (= ticks 60))
			(24
				(reverseBiaz setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(25 (= ticks 60))
			(26
				(switch talkCount
					(0
						(Say Reverse_Biaz 660 32 #dispose #caller self)
					)
					(1
						(Say Reverse_Biaz 660 33 #dispose #caller self)
					)
					(2
						(Say Reverse_Biaz 660 34 #dispose #caller self)
					)
					(3
						(Say Reverse_Biaz 660 35 #dispose #caller self)
					)
				)
			)
			(27
				(= ticks 90)
				(if (< (++ talkCount) 4) (= state (- state 7)))
			)
			(28
				(ego show:)
				(zing play:)
				(reverseBiaz
					view: 683
					setLoop: 2
					setCel: 0
					x: 241
					y: 71
					setCycle: EndLoop self
				)
			)
			(29 (= seconds 3))
			(30
				(giveTape init:)
				(SolvePuzzle 40 fGotCassette)
				(ego get: iCassette)
				(= seconds 3)
			)
			(31
				(SolvePuzzle 18 fGotBiazDrunk)
				(Say Reverse_Biaz 660 36 #dispose #caller self)
			)
			(32 (= seconds 3))
			(33
				(TimePrint 660 37)
				(= cycles 3)
			)
			(34
				(Bset fBiazDrunk)
				(curRoom newRoom: 690)
			)
		)
	)
)

(instance sTalkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(switch local4
					(0 (Say ego 660 38))
					(1 (Say ego 660 39))
					(2 (Say ego 660 40))
					(3 (Say ego 660 41))
					(4 (Say ego 660 42))
					(5 (Say ego 660 43))
					(6
						(globalTimer dispose: delete:)
						(sEnterBooth dispose:)
						(Say ego 660 44)
					)
				)
				(= ticks 60)
			)
			(1
				(switch local4
					(0
						(Say Reverse_Biaz 660 45 #dispose #caller self)
					)
					(1
						(Say Reverse_Biaz 660 46 #dispose #caller self)
					)
					(2
						(Say Reverse_Biaz 660 47 #dispose #caller self)
					)
					(3
						(Say Reverse_Biaz 660 48 #dispose #caller self)
					)
					(4 (= cycles 2))
					(5
						(Say Reverse_Biaz 660 49 #dispose #caller self)
					)
					(6
						(Say Reverse_Biaz 660 50 #dispose #caller self)
					)
				)
			)
			(2
				(++ local4)
				(DisableIcons)
				(if (> local4 6)
					(self setScript: sDoSex)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sDoSex of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(TimePrint 660 51)
				(theMusic fade: 0 15 12 1)
				(= ticks 60)
			)
			(1
				(theMusic2 number: 663 setLoop: -1 flags: 1 play:)
				(ego hide:)
				(reverseBiaz
					view: 682
					setLoop: 0
					setCel: 0
					x: 222
					y: 71
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(2 (= ticks 180))
			(3
				(reverseBiaz setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(4 (= ticks 240))
			(5
				(reverseBiaz
					setLoop: 2
					setCel: 0
					x: 235
					y: 71
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(6 (= ticks 120))
			(7
				(reverseBiaz
					setLoop: 3
					setCel: 0
					x: 263
					y: 71
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(8 (= ticks 120))
			(9
				(reverseBiaz
					setLoop: 2
					setCel: 0
					x: 235
					y: 71
					cycleSpeed: 10
					setCycle: CycleTo 5 1 self
				)
			)
			(10
				(reelThree setCycle: Forward)
				(reelFour setCycle: Reverse)
				(vuMeter view: 670 setLoop: 1 setCel: 0 setCycle: Forward)
				(= ticks 180)
			)
			(11
				(reverseBiaz
					setLoop: 2
					x: 235
					y: 71
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(12 (= ticks 120))
			(13
				(reverseBiaz
					setLoop: 4
					setCel: 0
					x: 255
					y: 73
					setCycle: EndLoop self
				)
			)
			(14
				(reverseBiaz
					setLoop: 5
					setCel: 0
					x: 213
					y: 72
					setCycle: EndLoop self
				)
			)
			(15 (= ticks 120))
			(16
				(reverseBiaz
					setLoop: 2
					setCel: 0
					x: 235
					y: 71
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(17 (= ticks 120))
			(18
				(reverseBiaz
					setLoop: 6
					setCel: 0
					x: 230
					y: 72
					setCycle: EndLoop self
				)
			)
			(19
				(reverseBiaz
					setLoop: 5
					setCel: 0
					x: 213
					y: 72
					setCycle: EndLoop self
				)
			)
			(20 (= ticks 240))
			(21
				(reverseBiaz
					setLoop: 7
					setCel: 0
					x: 213
					y: 73
					setCycle: EndLoop self
				)
			)
			(22 (= ticks 180))
			(23
				(if local6
					(SolvePuzzle 10 128)
				else
					(SolvePuzzle 16 129)
				)
				(keyboard dispose:)
				(curRoom drawPic: 1 6)
				(= ticks 120)
			)
			(24
				(TimePrint 660 52)
				(SolvePuzzle 40 130)
				(ego get: 6)
				(self cue:)
			)
			(25 (= ticks 120))
			(26
				(TimePrint 660 53)
				(TimePrint 660 54)
				(TimePrint 660 55)
				(curRoom newRoom: 690)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 249
		y 96
		description {the door}
		lookStr {This door leads to the control booth--and Reverse Biaz.}
		view 660
		signal $4001
		cycleSpeed 10
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (TimePrint 660 56))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance reverseBiaz of Prop
	(properties
		x 286
		y 64
		description {Reverse Biaz}
		lookStr {Reverse Biaz is the man the F.B.I. linked to backwards subliminal message recording at this studio. But he does looks cute.}
		view 662
		priority 5
		signal (| ignrAct fixPriOn stopUpdOn)
		cycleSpeed 10
	)
	
	(method (doit)
		(if (and cycler (not (IsObject cycler))) (= cycler 0))
		(super doit:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if local2
					(Say Reverse_Biaz 660 57 108)
				else
					(TimePrint 660 58)
				)
			)
			(verbTalk
				(if local2
					(curRoom setScript: sTalkScript)
				else
					(Say Reverse_Biaz 660 59 108)
				)
			)
			(verbUse
				(switch theItem
					(iChampagne
						(if local2
							(ego put: iChampagne)
							(curRoom setScript: sDrunkReverse)
						else
							(TimePrint 660 60)
						)
					)
					(else 
						(Say Reverse_Biaz 660 61 108)
					)
				)
			)
			(verbZipper
				(if local2
					(= local6 1)
					(curRoom setScript: sDoSex)
				else
					(TimePrint 660 62)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(= local0 2)
		(= local3 1)
		(sSession dispose:)
		(sMonitorRecording dispose:)
	)
)

(instance giveTape of View
	(properties
		x 241
		y 73
		view 683
		loop 3
		priority 4
		signal $0010
	)
)

(instance panelExtender of View
	(properties
		x 183
		y 83
		description {the control room mixer}
		sightAngle 40
		view 670
		loop 6
		priority 3
		signal $0011
	)
	
	(method (doVerb theVerb theItem)
		(controlPanel doVerb: theVerb theItem)
	)
)

(instance reelOne of Prop
	(properties
		x 214
		y 26
		description {the tape reel}
		sightAngle 40
		view 670
		loop 2
		signal $0001
		cycleSpeed 15
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (TimePrint 660 63))
			(3
				(if local2 (TimePrint 660 64) else (TimePrint 660 65))
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance reelTwo of Prop
	(properties
		x 236
		y 26
		description {the tape reel}
		sightAngle 40
		view 670
		loop 3
		signal $0001
		cycleSpeed 20
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(reelOne doVerb: theVerb theItem)
	)
)

(instance reelThree of Prop
	(properties
		x 261
		y 27
		description {the tape reel}
		sightAngle 40
		view 670
		loop 4
		signal $0001
		cycleSpeed 15
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(reelOne doVerb: theVerb theItem)
	)
)

(instance reelFour of Prop
	(properties
		x 283
		y 27
		description {the tape reel}
		sightAngle 40
		view 670
		loop 5
		signal $0001
		cycleSpeed 20
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(reelOne doVerb: theVerb theItem)
	)
)

(instance vuMeter of Prop
	(properties
		x 169
		y 29
		description {the VU meter}
		sightAngle 40
		lookStr {This meter measures dynamic levels in decibels, each of which is about 1/10th of a bel.}
		view 670
		signal $0001
		cycleSpeed 10
		detailLevel 4
	)
)

(class synthKey of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description {the key}
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 673
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 1
		frequency 0
		white 0
	)
	
	(method (doit &tmp userCurEvent temp1)
		(super doit:)
		(= userCurEvent (User curEvent?))
		(if
			(and
				(== local7 self)
				(!= self (= temp1 (localproc_2a2d userCurEvent)))
			)
			(self release:)
			(= local7 temp1)
			(if temp1 (temp1 depress:))
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(TimePrint 660 66)
				(TimePrint 660 67)
			)
			(3
				(if local2
					(TimePrint 660 68)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(self release:)
	)
	
	(method (depress param1)
		(if local8 (local8 release:) (= local8 0))
		(self setCel: 1)
		(if param1
			(++ local9)
			(theMusic send: 8 144 frequency 100)
		)
	)
	
	(method (release)
		(self setCel: 0)
		(theMusic send: 8 128 frequency 0)
	)
)

(instance keyboard of EventHandler
	(properties)
	
	(method (init &tmp temp0 temp1 newSynthKey temp3 temp4 temp5 temp6 temp7 temp8 temp9)
		(super init: &rest)
		(= temp4 0)
		(while (< temp4 31)
			(= temp1 (/ (= temp0 temp4) 12))
			(= temp3 (mod (= temp0 (- temp0 (* temp1 12))) 2))
			(if (< temp0 5) (-- temp3))
			(= temp7 ((= newSynthKey (synthKey new:)) view?))
			(= temp8
				(if temp3
					(switch temp0
						(0 1)
						(2 2)
						(4 3)
						(5 1)
						(7 2)
						(9 2)
						(11 3)
					)
				else
					0
				)
			)
			(if temp3
				(if (> temp0 4) (++ temp0))
				(= temp5
					(+ (= temp5 (* (/ temp0 2) 18)) (* temp1 126))
				)
				(= temp6 189)
			else
				(= temp5
					(+ (= temp5 (* (/ temp0 2) 18)) 18 (* temp1 126))
				)
				(= temp6 180)
			)
			(= temp9 (+ temp4 60))
			(newSynthKey
				setLoop: temp8
				x: temp5
				y: temp6
				frequency: temp9
				white: temp3
				ignoreActors:
				setPri: (if temp3 -1 else 15)
				stopUpd: 1
				init:
			)
			(self addToFront: newSynthKey)
			(++ temp4)
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(cond 
			(local7
				(if (== (event type?) mouseUp)
					(local7 release:)
					(= local7 0)
				)
				(event claimed: TRUE)
			)
			((== (event type?) mouseDown)
				(if
					(and
						(= temp1 (localproc_2a2d event))
						(!= ((theIconBar curIcon?) cursor?) 1)
					)
					(temp1 depress: 1)
					(= local7 temp1)
					(event claimed: TRUE)
				else
					(super handleEvent: event &rest)
				)
			)
			((== (event type?) keyDown)
				(if
				(!= (= temp1 (localproc_2850 event)) (keyboard at: -1))
					(temp1 depress: 1)
					(= local8 temp1)
					((Timer new:) setCycle: temp1 5)
				else
					(super handleEvent: event &rest)
				)
				(event claimed: 1)
			)
		)
	)
)

(instance synth of Feature
	(properties
		x 87
		y 102
		nsTop 88
		nsLeft 77
		nsBottom 115
		nsRight 100
		description {your synthesizer}
		sightAngle 40
		approachX 67
		approachY 131
		lookStr {This is the synthesizer Reverse Biaz wants you to play.}
	)
	
	(method (doVerb theVerb theItem)
		(if (== theVerb 3)
			(ego setScript: sUsesSynth)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance musicStands of Feature
	(properties
		x 55
		y 167
		nsTop 147
		nsLeft 31
		nsBottom 187
		nsRight 80
		description {the music stands}
		sightAngle 40
		lookStr {You see a bunch of music stands. Or is it a flock of music stands? Or is it a bevy of music stands? You always forget.}
	)
)

(instance recordingBooth of Feature
	(properties
		x 280
		y 52
		nsTop 22
		nsLeft 256
		nsBottom 83
		nsRight 304
		description {the control room}
		sightAngle 40
		lookStr {From his control room, Reverse can see the entire studio. He can speak to you through the soundproof glass via a talkback loudspeaker.}
	)
)

(instance overhangingMike of Feature
	(properties
		x 100
		y 63
		nsTop 56
		nsLeft 94
		nsBottom 70
		nsRight 107
		description {the overhead mike}
		sightAngle 40
		lookStr {You wonder how many evil recordings were done with this microphone.}
	)
)

(instance drums of Feature
	(properties
		x 178
		y 73
		nsTop 51
		nsLeft 135
		nsBottom 96
		nsRight 222
		description {the drum set}
		sightAngle 40
		lookStr {As in most studios, the drum set is behind glass to enhance the separation of his sound from the other musicians.}
	)
)

(instance music of Feature
	(properties
		x 96
		y 59
		nsTop 17
		nsLeft 48
		nsBottom 102
		nsRight 144
		description {the music}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(if local2 (TimePrint 660 69) else (TimePrint 660 70))
			)
			(3
				(if local2 (TimePrint 660 71) else (TimePrint 660 72))
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance synthPanel of Feature
	(properties
		x 159
		y 130
		nsTop 111
		nsBottom 149
		nsRight 319
		description {the synthesizer control panel}
		sightAngle 40
		lookStr {des Rever Records spared no expense programming this synthesizer for this session.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(if local2 (TimePrint 660 73) else (TimePrint 660 74))
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance controlPanel of Feature
	(properties
		x 238
		y 76
		nsTop 73
		nsLeft 194
		nsBottom 80
		nsRight 283
		description {the control room mixer}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (TimePrint 660 75))
			(3 (TimePrint 660 76))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance controlPanel2 of Feature
	(properties
		x 232
		y 58
		nsTop 50
		nsLeft 168
		nsBottom 67
		nsRight 296
		description {the studio control panel}
		sightAngle 40
		lookStr {This panel contains many digital reverb and special effects units, power amplifiers, patch bays and a copy of last month's Playboy.}
	)
)

(instance rewind of Sound
	(properties
		number 661
	)
)

(instance musicStop of Sound
	(properties
		number 662
	)
)

(instance glassesClink of Sound
	(properties
		number 664
		priority 15
	)
)

(instance zing of Sound
	(properties
		number 665
	)
)

(instance Reverse_Biaz of Talker
	(properties
		nsTop 15
		nsLeft 30
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
