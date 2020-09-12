;;; Sierra Script 1.0 - (do not remove this comment)
(script# 620)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm620 0
)

(local
	guardState
	showBiazPak
	showBiazFax
	showHammerPak
	showHammerFax
)
(instance rm620 of LLRoom
	(properties
		picture 620
		north 640
		south 600
	)
	
	(method (init)
		(ego init: normalize: 570)
		(HandsOn)
		(switch prevRoomNum
			(south
				(ego x: 92)
				(theMusic number: 620 flags: mNOPAUSE setLoop: -1 play:)
			)
			(north
				(ego setPri: 9 posn: 57 141)
				(curRoom setScript: sExitElevator)
			)
			(else 
				(ego posn: 160 160 edgeHit: 0)
				(theMusic number: 620 flags: mNOPAUSE setLoop: -1 play:)
			)
		)
		(LoadMany PICTURE 630)
		(LoadMany SOUND 621 622)
		(super init:)
		(board init: approachVerbs: verbLook verbDo verbUse)
		(guard init: approachVerbs: verbTalk verbUse verbDo)
		(elevator init: approachVerbs: verbDo verbUse)
		(elevatorLeft init: approachVerbs: verbDo verbUse)
		(plant init:)
		(mural init:)
		(fStairs init:)
		(fLamp1 init:)
		(fLamp2 init:)
		(fAshtray init: approachVerbs: verbLook verbDo)
		(sconceA init:)
		(sconceB init:)
		(clock init:)
		(breasts init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						123 189
						123 187
						313 187
						313 159
						224 159
						203 155
						198 145
						197 137
						209 130
						313 130
						313 116
						136 116
						132 119
						122 120
						114 120
						61 150
						61 151
						62 158
						58 163
						51 164
						16 189
						0 189
					yourself:
				)
		)
	)
	
	(method (doit)
		(if (and (ego edgeHit?) (== guardState 1))
			(= guardState 0)
			(guard
				setLoop: 0
				setCel: 0
				cycleSpeed: 30
				setCycle: EndLoop self
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbWalk
				(if (== curPic 630)
					(curRoom setScript: sReturnsFromBoard)
					1
				)
			)
			(verbLook
				(if (== curPic 630)
					(TimePrint 620 0)
				else
					(TimePrint 620 1)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(if (not (== n 640))
			(theMusic fade: 0 15 12 1)
		)
	)
)

(instance sExitElevator of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 2))
			(1
				(elevatorDing play:)
				(= cycles 10)
			)
			(2
				(elevatorDoor play:)
				(elevatorLeft setCycle: EndLoop)
				(elevator setCycle: EndLoop self)
			)
			(3
				(elevatorDoor stop:)
				(ego setMotion: MoveTo 89 150 self)
			)
			(4 (= seconds 2))
			(5
				(ego setPri: -1)
				(elevatorDoor play:)
				(elevatorLeft setCycle: BegLoop)
				(elevator setCycle: BegLoop self)
			)
			(6
				(elevatorDoor stop:)
				(elevator stopUpd: 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGuardWakes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guard
					setLoop: 0
					setCel: (guard lastCel:)
					cycleSpeed: 12
					setCycle: BegLoop self
				)
			)
			(1
				(= guardState 1)
				(Say The_Guard 620 2 #dispose)
				(HandsOn)
				(StartTimer 6 2 self)
			)
			(2
				(HandsOff)
				(guard setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(HandsOn)
				(guard setScript: sSleeping)
				(= guardState 0)
				(self dispose:)
			)
		)
	)
)

(instance sGuardSleeps of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= guardState 0)
				(TimePrint 620 3)
				(Say ego 620 4)
				(self cue:)
			)
			(1
				(Say The_Guard 620 5 #dispose)
				(guard setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(guard setScript: sSleeping)
				(= guardState 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGuardApproves of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					(showBiazPak (Say ego 620 6))
					(showBiazFax (Say ego 620 7))
					(showHammerPak (Say ego 620 8))
					(showHammerFax (Say ego 620 9))
					(else (Say ego 620 10))
				)
				(self cue:)
			)
			(1
				(cond 
					((Btst fBiazFailed)
						(Say The_Guard 620 11 #dispose)
						(HandsOn)
						(guard setScript: sBackToSleep)
						(self dispose:)
					)
					(showBiazPak
						(Say The_Guard 620 12 #dispose)
						(= showBiazPak 0)
						(SolvePuzzle 6 fShowedBiazPakToGuard)
					)
					(showHammerPak
						(Say The_Guard 620 13 #dispose)
						(= showHammerPak 0)
						(HandsOn)
						(guard setScript: sBackToSleep)
						(self dispose:)
					)
					(showBiazFax
						(Say The_Guard 620 14 #dispose)
						(= showBiazFax 0)
						(SolvePuzzle 6 fShowedBiazFaxToGuard)
					)
					(showHammerFax
						(Say The_Guard 620 15 #dispose)
						(= showHammerFax 0)
						(HandsOn)
						(guard setScript: sBackToSleep)
						(self dispose:)
					)
					(else
						(Say The_Guard 620 16 #dispose)
						(Bset fBiazReady)
					)
				)
				(self cue:)
			)
			(2
				(guard
					cycleSpeed: 12
					setLoop: 5
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(guard setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(guard
					setLoop: 4
					setCel: 0
					setCycle: ForwardCounter 2 self
				)
			)
			(5 (= seconds 2))
			(6
				(guard
					setLoop: 3
					setCel: (guard lastCel:)
					setCycle: BegLoop self
				)
			)
			(7
				(guard setLoop: 5 setCycle: EndLoop self)
			)
			(8
				(if (Btst fBlewGig)
					(Say The_Guard 620 17 #dispose)
					(Bset fBiazFailed)
					(HandsOn)
					(guard setScript: sBackToSleep)
					(self dispose:)
				else
					(Say The_Guard 620 18 #dispose)
					(guard
						cycleSpeed: 18
						setLoop: 0
						setCel: 0
						setCycle: EndLoop self
					)
				)
			)
			(9
				(guard setScript: sSleeping)
				(= seconds 3)
			)
			(10
				(elevatorDing play:)
				(= cycles 10)
			)
			(11
				(elevator setCycle: EndLoop self)
				(elevatorLeft setCycle: EndLoop)
				(elevatorDoor play:)
			)
			(12
				(elevatorDoor stop:)
				(ego setMotion: MoveTo 96 146 self)
			)
			(13
				(ego setPri: 9 setMotion: MoveTo 58 136 self)
			)
			(14 (ego setHeading: 180 self))
			(15
				(elevatorDoor play:)
				(elevator setCycle: BegLoop self)
				(elevatorLeft setCycle: BegLoop)
			)
			(16
				(elevatorDoor stop:)
				(curRoom newRoom: 640)
				(self dispose:)
			)
		)
	)
)

(instance sUsesBoard of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(theIconBar disable: ICON_CONTROL)
				(cast eachElementDo: #hide)
				(plant dispose:)
				(mural dispose:)
				(fStairs dispose:)
				(fLamp1 dispose:)
				(fLamp2 dispose:)
				(fAshtray dispose:)
				(sconceA dispose:)
				(sconceB dispose:)
				(clock dispose:)
				(breasts dispose:)
				(businessOne init:)
				(businessTwo init:)
				(businessThree init:)
				(businessFour init:)
				(businessFive init:)
				(businessSix init:)
				(businessSeven init:)
				(businessEight init:)
				(curRoom drawPic: 630)
				(InFirstPerson 1)
				(Display 620 19
					p_at 86 67
					p_color myDisplayColor
					p_font smallFont
				)
				(Display 620 20
					p_at 88 79
					p_color myDisplayColor
					p_font smallFont
				)
				(Display 620 21
					p_at 88 91
					p_color myDisplayColor
					p_font smallFont
				)
				(Display 620 22
					p_at 89 103
					p_color myDisplayColor
					p_font smallFont
				)
				(Display 620 23
					p_at 90 115
					p_color myDisplayColor
					p_font smallFont
				)
				(Display 620 24
					p_at 90 127
					p_color myDisplayColor
					p_font smallFont
				)
				(Display 620 25
					p_at 92 139
					p_color myDisplayColor
					p_font smallFont
				)
				(Display 620 26
					p_at 93 151
					p_color myDisplayColor
					p_font smallFont
				)
				(board dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sReturnsFromBoard of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(theIconBar enable: ICON_CONTROL)
				(curRoom drawPic: (curRoom picture?))
				(cast eachElementDo: #show)
				(businessOne dispose:)
				(businessTwo dispose:)
				(businessThree dispose:)
				(businessFour dispose:)
				(businessFive dispose:)
				(businessSix dispose:)
				(businessSeven dispose:)
				(businessEight dispose:)
				(plant init:)
				(mural init:)
				(fStairs init:)
				(fLamp1 init:)
				(fLamp2 init:)
				(fAshtray init:)
				(sconceA init:)
				(sconceB init:)
				(clock init:)
				(breasts init:)
				(board init: approachVerbs: verbLook verbDo verbUse)
				(InFirstPerson 0)
			)
		)
	)
)

(instance sBackToSleep of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= guardState 0)
				(guard setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(1 (guard setScript: sSleeping))
			(2 (self dispose:))
		)
	)
)

(instance sSleeping of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guard
					setLoop: 1
					setCel: 0
					cycleSpeed: 30
					setCycle: EndLoop self
				)
			)
			(1 (= cycles 20))
			(2 (guard setCycle: BegLoop self))
			(3 (= cycles 20))
			(4 (self changeState: 0))
		)
	)
)

(instance guard of Prop
	(properties
		x 274
		y 124
		description {the guard}
		sightAngle 40
		approachX 244
		approachY 166
		view 622
		loop 1
		priority 11
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init:)
		(self setScript: sSleeping)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(if (== guardState 0)
					(guard setScript: sGuardWakes)
				else
					(ll5Timer dispose: delete:)
					(if (Btst fKnowBiazAddress)
						(curRoom setScript: sGuardApproves)
					else
						(guard setScript: sGuardSleeps)
					)
				)
			)
			(verbUse
				(if (== guardState 1)
					(ll5Timer dispose: delete:)
					(switch theItem
						(iDataMan
							(cond 
								((== ((Inventory at: iDataMan) state?) datamanBIAZ)
									(= showBiazPak 1)
									(curRoom setScript: sGuardApproves)
								)
								((== ((Inventory at: iDataMan) state?) datamanHAMMER)
									(= showHammerPak 1)
									(curRoom setScript: sGuardApproves)
								)
								(else
									(Say The_Guard 620 27 #dispose)
									(guard setScript: sBackToSleep)
								)
							)
						)
						(iBiazFax
							(= showBiazFax 1)
							(curRoom setScript: sGuardApproves)
						)
						(iHammerFax
							(= showHammerFax 1)
							(curRoom setScript: sGuardApproves)
						)
						(else 
							(super doVerb: theVerb theItem &rest)
							(guard setScript: sBackToSleep)
						)
					)
				else
					(TimePrint 620 28)
				)
			)
			(verbLook
				(if (== guardState 0)
					(TimePrint 620 29)
				else
					(TimePrint 620 30)
				)
			)
			(verbDo
				(if (== guardState 1)
					(Say The_Guard 620 31 #dispose)
				else
					(TimePrint 620 32)
				)
			)
			(verbZipper
				(TimePrint 620 33)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance elevator of Prop
	(properties
		x 61
		y 148
		description {the elevator}
		sightAngle 40
		view 620
		priority 8
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 620 34)
			)
			(verbDo
				(TimePrint 620 35)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance elevatorLeft of Prop
	(properties
		x 45
		y 152
		description {the elevator}
		sightAngle 40
		view 620
		loop 1
		priority 9
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
	
	(method (doVerb theVerb theItem)
		(elevator doVerb: theVerb theItem &rest)
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

(instance board of Feature
	(properties
		x 10
		y 131
		nsTop 98
		nsLeft 9
		nsBottom 165
		nsRight 38
		description {the Shill Building's directory}
		sightAngle 40
		approachX 61
		approachY 178
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(curRoom setScript: sUsesBoard)
			)
			(verbDo
				(curRoom setScript: sUsesBoard)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance businessOne of Feature
	(properties
		x 157
		y 71
		nsTop 66
		nsLeft 93
		nsBottom 76
		nsRight 221
		description {the directory}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 620 36 #at -1 20)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromBoard)
				1
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance businessTwo of Feature
	(properties
		x 156
		y 82
		nsTop 78
		nsLeft 92
		nsBottom 86
		nsRight 221
		description {the directory}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 620 37 #at -1 20)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromBoard)
				1
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance businessThree of Feature
	(properties
		x 154
		y 94
		nsTop 90
		nsLeft 86
		nsBottom 99
		nsRight 223
		description {the directory}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 620 38 #at -1 20)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromBoard)
				1
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance businessFour of Feature
	(properties
		x 153
		y 106
		nsTop 102
		nsLeft 85
		nsBottom 111
		nsRight 222
		description {the directory}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Bset fKnowBiazAddress)
				(SolvePuzzle 3 fFindBiazRoomOnBoard)
				(TimePrint 620 39 #at -1 20)
			)
			(verbDo
				(Bset fKnowBiazAddress)
				(SolvePuzzle 3 fFindBiazRoomOnBoard)
				(TimePrint 620 39 #at -1 20)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromBoard)
				1
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance businessFive of Feature
	(properties
		x 155
		y 118
		nsTop 114
		nsLeft 88
		nsBottom 123
		nsRight 222
		description {the directory}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 620 40 #at -1 20)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromBoard)
				1
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance businessSix of Feature
	(properties
		x 155
		y 130
		nsTop 126
		nsLeft 87
		nsBottom 135
		nsRight 224
		description {the directory}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 620 41 #at -1 20)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromBoard)
				1
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance businessSeven of Feature
	(properties
		x 156
		y 142
		nsTop 138
		nsLeft 89
		nsBottom 147
		nsRight 224
		description {the directory}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 620 42 #at -1 20)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromBoard)
				1
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance businessEight of Feature
	(properties
		x 156
		y 154
		nsTop 150
		nsLeft 89
		nsBottom 159
		nsRight 223
		description {the directory}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 620 43 #at -1 20)
			)
			(verbWalk
				(curRoom setScript: sReturnsFromBoard)
				1
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plant of Feature
	(properties
		x 120
		y 95
		nsTop 36
		nsLeft 104
		nsBottom 114
		nsRight 137
		description {the plant}
		lookStr {This plant is obviously well-maintained, but superfluous to your mission.}
	)
)

(instance mural of Feature
	(properties
		x 230
		y 47
		nsLeft 142
		nsBottom 95
		nsRight 319
		description {the mural}
		lookStr {It is quite an interesting mural.}
	)
)

(instance fStairs of Feature
	(properties
		x 229
		y 164
		nsTop 139
		nsLeft 139
		nsBottom 189
		nsRight 319
		description {the staircase}
		sightAngle 40
		onMeCheck $2000
		lookStr {A staircase winds its way upwards, but a velvet rope across the bottom indicates it's not to be used under penalty of law. It looks like the elevator is your only hope, Patti.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 620 44)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fLamp1 of Feature
	(properties
		x 131
		y 163
		nsTop 139
		nsLeft 124
		nsBottom 188
		nsRight 138
		description {the lamp}
		sightAngle 40
		lookStr {A lovely Art Deco lamp rises above the staircase.}
	)
)

(instance fLamp2 of Feature
	(properties
		x 294
		y 118
		nsTop 98
		nsLeft 287
		nsBottom 138
		nsRight 302
		description {the lamp}
		sightAngle 40
		lookStr {A lovely Art Deco lamp rises above the staircase.}
	)
)

(instance fAshtray of Feature
	(properties
		x 51
		y 150
		nsTop 141
		nsLeft 46
		nsBottom 160
		nsRight 56
		description {the ashtray}
		sightAngle 40
		approachX 70
		approachY 160
		lookStr {Carefully examining the ashtray for a disco pass, for a brief moment you think you're playing "Leisure Suit Larry 1: In the Land of the Lounge Lizards." Of course, you're not.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 620 45)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sconceA of Feature
	(properties
		x 21
		y 81
		nsTop 77
		nsLeft 5
		nsBottom 86
		nsRight 37
		description {the sconce}
		sightAngle 40
		lookStr {My, the Shill Building has tasteful sconces.}
	)
)

(instance sconceB of Feature
	(properties
		x 94
		y 71
		nsTop 69
		nsLeft 85
		nsBottom 74
		nsRight 103
		description {the sconce}
		sightAngle 40
		lookStr {My, the Shill Building has tasteful sconces.}
	)
)

(instance clock of Feature
	(properties
		x 230
		y 112
		nsTop 107
		nsLeft 215
		nsBottom 125
		nsRight 245
		description {the clock}
		sightAngle 40
		lookStr {A lovely Deco time clock is built into the end of the Security Guard's counter for those employees still paid by the hour.}
	)
)

(instance breasts of Feature
	(properties
		x 191
		y 199
		z 80
		nsTop 34
		nsLeft 182
		nsBottom 44
		nsRight 201
		description {the mural}
		sightAngle 40
		lookStr {What? Patti! When did you develop an interest in other women?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 620 46)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance The_Guard of Talker
	(properties
		nsTop 15
		nsLeft 35
		view 1622
		loop 3
		viewInPrint TRUE
		name "The Guard"
	)
	
	(method (init)
		(= bust guardBust)
		(= eyes guardEyes)
		(= mouth guardMouth)
		(super init: &rest)
	)
)

(instance guardBust of Prop
	(properties
		view 1622
		loop 1
	)
)

(instance guardEyes of Prop
	(properties
		nsTop 32
		nsLeft 21
		view 1622
		loop 2
		cycleSpeed 70
	)
)

(instance guardMouth of Prop
	(properties
		nsTop 36
		nsLeft 15
		view 1622
		cycleSpeed 5
	)
)
