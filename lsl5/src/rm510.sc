;;; Sierra Script 1.0 - (do not remove this comment)
(script# 510)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Intrface)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm510 0
)

(local
	talkCount
	[local1 3]
	local4
	local5
)
(instance rm510 of LLRoom
	(properties
		lookStr {The lobby of "The Hard Disk Cafe" is filled with a tremendously exciting (to nerds) display of ancient computer memorabilia. Your pulse quickens just looking around the place.}
		picture 510
		north 525
		south 500
	)
	
	(method (init)
		(LoadMany VIEW 515 512 513 514 511 510)
		(Load SOUND 510)
		(LDoor init: approachVerbs: verbDo)
		(switch prevRoomNum
			(north
				(ego edgeHit: 0 init: normalize:)
			)
			(535
				(HandsOn)
				(Bset fHardDiskCafeDone)
				((Inventory at: iMembershipTape) state: HDCdone)
				(ego
					x: (door approachX?)
					y: (door approachY?)
					init:
					normalize:
					edgeHit: 0
				)
			)
			(520
				(ego init: normalize: posn: 137 77)
				(if (ego has: iMembershipTape)
					(HandsOff)
					(curRoom setScript: sExitNorth)
				else
					(HandsOn)
				)
			)
			(500
				(ego init: normalize: posn: 334 219)
				(theMusic fade: 127 10 10 0)
				(self setScript: sEnterSouth)
			)
			(else 
				(= currentCity NEW_YORK)
				(if (not prevRoomNum)
					(WrapMusic firstSound: 500 lastSound: 502)
					(theMusic number: 500 play: WrapMusic flags: mNOPAUSE setLoop: 1)
				)
				(ego init: normalize: posn: 334 219)
				(self setScript: sEnterSouth)
			)
		)
		(door init:)
		(super init:)
		(if (and (!= prevRoomNum 535) (not (Btst fHardDiskCafeDone)))
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 0
							319 0
							319 114
							270 99
							258 104
							245 100
							251 90
							238 87
							218 94
							208 82
							218 71
							215 70
							210 75
							172 65
							169 58
							146 59
							147 70
							106 82
							81 96
							92 100
							98 108
							84 114
							62 113
							50 108
							29 108
							4 125
							6 186
							270 187
							272 189
							0 189
						yourself:
					)
			)
			(woman init: addToPic:)
			(womansMouth init: ignoreActors: 1 setScript: sMouth)
			(phone init: setCel: 1 stopUpd:)
		else
			(if (== prevRoomNum 535)
				(WrapMusic firstSound: 500 lastSound: 502)
				(theMusic number: 500 play: WrapMusic flags: mNOPAUSE setLoop: 1)
			)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 0
							319 0
							319 114
							270 99
							258 104
							245 100
							251 90
							238 87
							218 94
							208 82
							218 71
							215 70
							210 75
							172 65
							169 58
							146 59
							147 70
							109 83
							81 96
							92 100
							98 108
							84 114
							62 113
							50 108
							29 108
							4 125
							4 187
							270 187
							272 189
							0 189
						yourself:
					)
			)
			(phone init: setCel: 0 approachVerbs: verbDo verbTalk stopUpd:)
		)
		(if (== ((Inventory at: iMembershipTape) owner?) 510)
			(tape init: setCel: 255)
		)
		(if (== ((Inventory at: iMembershipTape) state?) HDCnotYet)
			(Load SOUND 512 442)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						50 168
						44 161
						52 156
						149 157
						157 163
						145 169
						77 169
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						69 129
						80 124
						131 124
						141 130
						130 135
						79 135
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						120 110
						129 103
						173 103
						186 109
						179 114
						173 117
						126 117
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						142 147
						140 141
						148 135
						201 135
						213 143
						204 150
						188 152
						159 152
					yourself:
				)
		)
		(musicBox init: approachVerbs: verbDo verbUse stopUpd:)
		(maitreD init: approachVerbs: verbTalk verbDo verbUse)
		(tapeReader init: approachVerbs: verbDo verbUse)
		(diskDrive init:)
		(pet init:)
		(appleII init:)
		(cashRegister init:)
		(monitor init:)
		(atari400 init:)
		(tapeDrive init:)
		(cactus1 init:)
		(cactus2 init:)
		(pianoRoll init:)
		(punchCardMachine init:)
		(podium init:)
		(desk init:)
		(bullBoard init:)
		(chair init:)
	)
	
	(method (doit)
		(super doit:)
		(if (and (ego mover?) modelessDialog)
			(modelessDialog dispose:)
		)
		(cond 
			(script)
			((IsObjectOnControl ego cRED) (HandsOff) (self setScript: sExitSouth))
		)
	)
	
	(method (newRoom n)
		(theMusic fade: 127 10 10 0)
		(super newRoom: n)
	)
	
	(method (notify phoneNum)
		(switch phoneNum
			(-1
				(TimePrint 510 0)
			)
			(4668
				(if (not (Btst fLimoHere))
					(Bset fLimoHere)
					(SolvePuzzle 2 fCallForLimoInNewYork)
					(TimePrint 510 1)
					(Say ego 510 2)
					(TimePrint 510 3)
				else
					(TimePrint 510 4)
				)
			)
			(else
				(TimePrint 510 5)
			)
		)
	)
)

(instance sExitNorth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: PolyPath (door approachX?) (door approachY?) self
				)
			)
			(1
				(door locked: 0 exitType: 0 open:)
			)
		)
	)
)

(instance sEnterSouth of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 293 183 self)
			)
			(1
				(HandsOn)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance sBribe of Script
	
	(method (changeState newState &tmp n [str 30])
		(switch (= state newState)
			(0
				(Say ego 510 6)
				(= ticks 60)
			)
			(1
				(cond 
					((== ((Inventory at: iMembershipTape) state?) HDCnotYet)
						(Say the_maitre_d_ 510 7 #dispose #caller self)
					)
					((== ((Inventory at: iMembershipTape) state?) HDCdone)
						(Say the_maitre_d_ 510 8 #dispose)
						(HandsOn)
						(self dispose:)
					)
					(else
						(Say the_maitre_d_ 510 9 #dispose)
						(HandsOn)
						(self dispose:)
					)
				)
			)
			(2 (= ticks 67))
			(3
				(= n 0)
				(while (< n 1)
					(= str 0)
					(GetInput @str 6
						{How much do you wish to bribe the Maitre d'.}
						#title {Grease That Palm}
					)
					(= n (ReadNumber @str))
				)
				(if (> n 99)
					(HandsOff)
					(SolvePuzzle 3 fTapePrinted)
					(ego put: iMoney)
					(Say the_maitre_d_ 510 10 #dispose)
					(= local4 TRUE)
					((Inventory at: iMoney) state: 1)
					(curRoom setScript: sMD)
				else
					(Say the_maitre_d_ 510 11 #dispose)
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)

(instance sMouth of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(womansMouth setCycle: Forward cycleSpeed: (Random 6 12))
				(= cycles (Random 48 90))
			)
			(1
				(womansMouth setCel: 0)
				(= cycles (Random 30 60))
			)
			(2 (self init:))
		)
	)
)

(instance sMusicBox of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(musicBox setCycle: Forward)
				(musicBoxCloseUp init: setCycle: Forward)
				(= seconds 3)
			)
			(1
				(SolvePuzzle 12 fHackedMembershipTape)
				(TimePrint 510 12 #at 100 10)
				(= seconds 3)
			)
			(2
				(musicBoxCloseUp dispose:)
				(musicBox setCel: 0 stopUpd:)
				(HandsOn)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance sPhone of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 511
					posn: (- (ego x?) 10) (ego y?)
					setLoop: 2
					setCel: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(1
				(phone setCel: 1)
				(ego setCycle: EndLoop self)
			)
			(2
				(User canInput: TRUE)
				((ScriptID DIALER 0) init: 1 self)
			)
			(3 (ego setCycle: CycleTo 4 -1 self))
			(4
				(phone setCel: 0 stopUpd:)
				(ego setCycle: BegLoop self)
			)
			(5
				(ego normalize: 550 posn: (+ (ego x?) 10) (ego y?))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego
					setMotion: MoveTo (+ (ego x?) 40) (+ (ego y?) 40) self
				)
			)
			(1 (curRoom newRoom: 500))
		)
	)
)

(instance sMD of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 270 self)
			)
			(1
				(if local4 (= state 19))
				(= cycles 2)
			)
			(2
				(cond 
					((== ((Inventory at: iMembershipTape) state?) HDCnotYet)
						(switch (++ talkCount)
							(1 (Say ego 510 13))
							(2 (Say ego 510 14))
							(3 (Say ego 510 15))
							(4 (Say ego 510 16))
							(5 (Say ego 510 17))
							(6 (Say ego 510 18))
							(7 (Say ego 510 19))
							(8 (Say ego 510 20))
							(9 (Say ego 510 21))
							(else  (Say ego 510 22))
						)
					)
					((== ((Inventory at: iMembershipTape) owner?) 510)
						(Say ego 510 23)
					)
					((== ((Inventory at: iMembershipTape) state?) HDCdone)
						(Say ego 510 24)
					)
					((Btst fMetMichelle)
						(Say ego 510 25)
					)
					((== ((Inventory at: iMembershipTape) state?) HDCgotTape) (Say ego 510 26))
				)
				(= cycles 3)
			)
			(3
				(cond 
					((== ((Inventory at: iMembershipTape) state?) HDCnotYet)
						(switch talkCount
							(1
								(Say the_maitre_d_ 510 27 #dispose)
							)
							(2
								(Say the_maitre_d_ 510 27 #dispose)
							)
							(3
								(Say the_maitre_d_ 510 27 #dispose)
							)
							(4
								(Say the_maitre_d_ 510 27 #dispose)
							)
							(5
								(Say the_maitre_d_ 510 28 #dispose)
							)
							(6
								(Say the_maitre_d_ 510 29 #dispose)
							)
							(7
								(Say the_maitre_d_ 510 30 #dispose)
							)
							(8
								(Say the_maitre_d_ 510 31 #dispose)
							)
							(9
								(Say the_maitre_d_ 510 32 #dispose 139 self)
								(= local4 1)
								((Inventory at: iMembershipTape) state: HDCgotTape)
							)
							(else 
								(Say the_maitre_d_ 510 33 #dispose)
							)
						)
					)
					((== ((Inventory at: iMembershipTape) owner?) 510)
						(Say the_maitre_d_ 510 34 #dispose)
					)
					((== ((Inventory at: iMembershipTape) state?) HDCdone)
						(Say the_maitre_d_ 510 8 #dispose)
					)
					((Btst fMetMichelle)
						(curRoom setScript: sDoubleTalk)
					)
					((== ((Inventory at: iMembershipTape) state?) HDCgotTape)
						(Say the_maitre_d_ 510 35 #dispose)
					)
				)
				(if (not local4)
					(HandsOn)
					(self dispose:)
				)
			)
			(4
				(= state 19)
				(= cycles 2)
				(SolvePuzzle 3 fTapePrinted)
				(TimePrint 510 36 #at -1 185)
			)
			(20
				(maitreD
					init:
					view: 515
					setCycle: Walk
					setLoop: -1
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo 133 86 self
				)
				(ego setMotion: MoveTo 153 (ego y?))
				(= local4 0)
			)
			(21
				(typingFX play:)
				(maitreD view: 512 setLoop: 1 setCycle: Forward)
				(screen init:)
				(ego setMotion: 0 setHeading: 315)
				(= seconds 3)
			)
			(22
				(typingFX stop:)
				(maitreD setCel: 0)
				(screen setCycle: Forward)
				(= seconds 3)
			)
			(23
				(typingFX play:)
				(maitreD setCycle: Forward)
				(screen setCel: 0)
				(= seconds 3)
			)
			(24
				(typingFX stop:)
				(maitreD setCel: 0)
				(tape init: setCycle: EndLoop self)
				(screen dispose:)
			)
			(25
				(Say the_maitre_d_ 510 37 #dispose #caller self)
			)
			(26
				(maitreD
					view: 515
					setCycle: Walk
					setLoop: -1
					setMotion: MoveTo 70 90 self
				)
			)
			(27
				(maitreD view: 512 setLoop: 0)
				((Inventory at: iMembershipTape) owner: 510)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sDoubleTalk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say the_maitre_d_ 510 38 #dispose #caller self)
			)
			(1
				(Say the_maitre_d_ 510 39 #dispose)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGetTape of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 511
					setCel: 0
					setLoop: 0
					cycleSpeed: 6
					setCycle: CycleTo 6 1 self
					get: 14
				)
			)
			(1
				(paperRip play:)
				(ego setCycle: EndLoop self)
				(tape dispose:)
			)
			(2
				(ego setLoop: 1 normalize:)
				(SolvePuzzle 4 fGotMembershipTape)
				(TimePrint 510 40)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance phone of View
	(properties
		x 59
		y 105
		z 20
		nsTop 71
		nsLeft 50
		nsBottom 85
		nsRight 72
		description {the telephone}
		sightAngle 90
		approachX 81
		approachY 96
		view 510
		loop 4
		priority 5
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst fHardDiskCafeDone)
					(TimePrint 510 41)
				else
					(TimePrint 510 42)
				)
			)
			(verbTalk
				(self doVerb: verbDo &rest)
			)
			(verbDo
				(cond 
					((Btst fLimoHere)
						(TimePrint 510 43)
					)
					((Btst fHardDiskCafeDone)
						(curRoom setScript: sPhone)
					)
					(else
						(Say ego 510 44)
						(TimePrint 510 45)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance screen of Prop
	(properties
		x 123
		y 46
		nsTop 40
		nsLeft 110
		nsBottom 49
		nsRight 130
		description {the computer terminal}
		sightAngle 90
		view 510
		loop 2
		priority 15
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
	)
)

(instance woman of View
	(properties
		x 80
		y 106
		description {Bertha}
		lookStr {A large aggressive woman has been talking on the telephone for as long as you've been here.}
		view 513
		priority 7
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Say ego 510 44)
				(TimePrint 510 46)
			)
			(verbTalk
				(Say ego 510 47)
				(TimePrint 510 46)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance womansMouth of Prop
	(properties
		x 74
		y 75
		description {Bertha's head}
		view 513
		loop 1
		priority 7
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
	)
)

(instance musicBox of Prop
	(properties
		x 39
		y 189
		nsTop 173
		nsLeft 19
		nsBottom 189
		nsRight 72
		description {the music box}
		approachX 58
		approachY 186
		view 514
		priority 15
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 510 48)
				(if (ego has: iMembershipTape)
					(TimePrint 510 49 #at -1 185)
				else
					(TimePrint 510 50)
				)
			)
			(verbDo
				(musicBoxFX play: self)
				(theMusic fade: 80 10 10 0)
				(TimePrint 510 51 #at 100 10)
			)
			(verbTalk
				(TimePrint 510 52)
			)
			(verbUse
				(switch theItem
					(iMembershipTape
						(if (not (> ((Inventory at: iMembershipTape) state?) HDCgotTape))
							(theMusic fade: 80 10 10 0)
							(musicBoxFX play: self)
							((Inventory at: iMembershipTape) state: HDChacked)
							(TimePrint 510 53)
							(curRoom setScript: sMusicBox)
						else
							(TimePrint 510 54)
						)
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
	
	(method (cue)
		(super cue:)
		(theMusic fade: 127 10 10 0)
	)
)

(instance musicBoxCloseUp of Prop
	(properties
		x 71
		y 174
		view 511
		loop 1
		priority 15
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance tape of Prop
	(properties
		x 112
		y 59
		description {your membership tape}
		sightAngle 90
		approachX 117
		approachY 82
		lookStr {The membership tape you wanted so badly now hangs limply from the side of the paper tape reader, twisting slowly, slowly, in the wind.}
		view 510
		loop 3
		priority 3
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: verbDo verbUse)
		(tapeReader approachX: approachX approachY: approachY)
	)
	
	(method (dispose)
		(super dispose:)
		(tapeReader approachX: 139 approachY: 77)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sGetTape)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance maitreD of Actor
	(properties
		x 70
		y 90
		description {the maitre d'}
		approachX 103
		approachY 89
		lookStr {You are quite envious of those that can look stupid and snooty at the same time.}
		view 512
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Say ego 510 56)
				(if (ego has: iMembershipTape)
					(Say the_maitre_d_ 510 57 #dispose)
				else
					(Say the_maitre_d_ 510 58 #dispose)
				)
			)
			(verbTalk
				(curRoom setScript: sMD)
			)
			(verbUse
				(if (== ((Inventory at: iMembershipTape) owner?) 510)
					(Say the_maitre_d_ 510 59 #dispose)
				else
					(switch theItem
						(iMoney
							(HandsOff)
							(curRoom setScript: sBribe)
						)
						(iCreditCards
							(if (== ((Inventory at: iMembershipTape) state?) HDCgotTape)
								(Say ego 510 60)
								(Say the_maitre_d_ 510 61 #dispose)
								(SolvePuzzle 5 fBribedMaitreD)
								((Inventory at: iMembershipTape) state: HDCbribed)
								(ego put: iCreditCards)
								(= local4 1)
								(curRoom setScript: sMD)
							else
								(Say the_maitre_d_ 510 62 #dispose)
							)
						)
						(iNapkin
							(Say ego 510 63)
							(Say the_maitre_d_ 510 64 #dispose #caller self)
							(ego put: iNapkin curRoomNum)
							(theIconBar disable: ICON_ITEM curIcon: (theIconBar at: ICON_WALK))
							(theGame setCursor: ((theIconBar curIcon?) cursor?))
						)
						(iDayTrotter
							(Say ego 510 65)
							(Say the_maitre_d_ 510 66 #dispose)
						)
						(else 
							(super doVerb: theVerb theItem &rest)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(TimePrint 510 55 #at -1 185)
	)
)

(instance LDoor of Prop
	(properties
		x 139
		y 66
		description {the swinging doors}
		approachX 165
		approachY 70
		view 510
		priority 2
		signal (| fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(door doVerb: theVerb theItem)
	)
)

(instance door of Door
	(properties
		x 179
		y 63
		heading 180
		nsTop 8
		nsLeft 141
		nsBottom 63
		nsRight 177
		description {the swinging doors}
		approachX 165
		approachY 70
		lookStr {You presume these doors lead to the cafe proper.}
		view 510
		loop 1
		priority 2
		signal fixPriOn
		entranceTo 525
		locked 1
		moveToX 156
		moveToY 60
		enterType 0
		exitType 0
	)
	
	(method (init)
		(= doubleDoor LDoor)
		(super init:)
		(self approachVerbs: verbDo)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 510 67)
			)
			(verbUse
				(switch theItem
					(iMembershipTape
						(TimePrint 510 68)
					)
					(iGoldCard
						(TimePrint 510 69)
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

(instance the_maitre_d_ of Talker
	(properties
		nsTop 33
		nsLeft 22
		view 1512
		loop 3
		viewInPrint TRUE
		name "the maitre d'"
	)
	
	(method (init)
		(= bust talkerBust)
		(= eyes talkerEyes)
		(= mouth talkerMouth)
		(super init:)
	)
)

(instance talkerBust of View
	(properties
		view 1512
		loop 1
	)
)

(instance talkerEyes of Prop
	(properties
		nsTop 11
		nsLeft 26
		view 1512
		loop 2
		cycleSpeed 30
	)
)

(instance talkerMouth of Prop
	(properties
		nsTop 24
		nsLeft 12
		view 1512
	)
)

(instance tapeReader of Feature
	(properties
		x 112
		y 65
		nsTop 38
		nsLeft 101
		nsBottom 73
		nsRight 139
		description {the paper tape machine}
		sightAngle 180
		approachX 139
		approachY 77
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 510 70)
				(if (== ((Inventory at: iMembershipTape) owner?) 510)
					(TimePrint 510 71)
				)
			)
			(3
				(if (== ((Inventory at: iMembershipTape) owner?) 510)
					(tape doVerb: verbLook)
				else
					(curRoom newRoom: 520)
				)
			)
			(verbTalk
				(TimePrint 510 72)
			)
			(verbUse
				(switch theItem
					(iMembershipTape
						(Bset fGetIntoHDC)
						(curRoom newRoom: 520)
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

(instance diskDrive of Feature
	(properties
		x 301
		y 82
		nsTop 56
		nsLeft 283
		nsBottom 108
		nsRight 319
		description {the antique disk drive}
		sightAngle 90
		lookStr {This early disk drive was the size of a washing machine and held an enormous amount of data--over one megabyte.}
	)
	
	(method (doVerb theVerb theItem)
		(if (!= theVerb verbLook)
			(TimePrint 510 73)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance pet of Feature
	(properties
		x 108
		y 102
		nsTop 92
		nsLeft 89
		nsBottom 113
		nsRight 128
		description {the Pet Computer}
		sightAngle 90
		onMeCheck cBLUE
		lookStr {You haven't seen a Pet Computer since the early days of "InfoWorld!"}
	)
	
	(method (doVerb theVerb theItem)
		(if (!= theVerb verbLook)
			(TimePrint 510 74)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance appleII of Feature
	(properties
		x 173
		y 112
		nsTop 101
		nsLeft 154
		nsBottom 124
		nsRight 192
		description {the Apple II computer}
		sightAngle 90
		onMeCheck cBLUE
		lookStr {You cut your first computer teeth on an old integer BASIC Apple II. It wasn't even a Plus!}
	)
	
	(method (doVerb theVerb theItem)
		(if (!= theVerb verbLook)
			(TimePrint 510 74)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance cashRegister of Feature
	(properties
		x 148
		y 89
		nsTop 78
		nsLeft 128
		nsBottom 100
		nsRight 169
		description {the cash register}
		sightAngle 90
		onMeCheck cBLUE
		lookStr {This NCR baby was one of the first microprocessor-controlled point-of-sale cash registers.}
	)
	
	(method (doVerb theVerb theItem)
		(if (!= theVerb verbLook)
			(TimePrint 510 74)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance monitor of Feature
	(properties
		x 124
		y 135
		nsTop 126
		nsLeft 111
		nsBottom 144
		nsRight 138
		description {the green screen monitor}
		sightAngle 90
		onMeCheck cBLUE
		lookStr {This ancient device could display text in any color you wanted, as long as it was green on black.}
	)
	
	(method (doVerb theVerb theItem)
		(if (!= theVerb verbLook)
			(TimePrint 510 74)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance atari400 of Feature
	(properties
		x 78
		y 144
		nsTop 137
		nsLeft 58
		nsBottom 152
		nsRight 99
		description {the Atari 400 computer}
		sightAngle 90
		onMeCheck cBLUE
		lookStr {Boy, does that Atari 400 bring back fond memories of your misspent youth, playing "Frogger" when your classmates were dating!}
	)
	
	(method (doVerb theVerb theItem)
		(if (!= theVerb verbLook)
			(TimePrint 510 74)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance tapeDrive of Feature
	(properties
		x 16
		y 71
		nsTop 40
		nsBottom 103
		nsRight 32
		description {the antique tape drive}
		sightAngle 90
		lookStr {This is one of the world's first tape drives, revolutionary in its day, it seems incredibly quaint today. Why it couldn't even hold "Space Quest" let alone a "King's Quest!"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbUse
				(switch theItem
					(iMembershipTape
						(TimePrint 510 75)
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

(instance pianoRoll of Feature
	(properties
		x 243
		y 17
		nsTop 9
		nsLeft 220
		nsBottom 26
		nsRight 266
		description {the piano roll}
		sightAngle 90
		lookStr {The player piano was an early use of punched holes to control a mechanical device. A vacuum was applied to 88 holes in a metal bar, with each hole indicating a different musical pitch. Where holes occurred in the moving paper, the corresponding notes were played.}
	)
	
	(method (doVerb)
		(TimePrint 510 76)
	)
)

(instance punchCardMachine of Feature
	(properties
		x 253
		y 61
		nsTop 34
		nsLeft 222
		nsBottom 88
		nsRight 284
		description {the punch card machine}
		sightAngle 40
		lookStr {This punch card reader dates from the 1890 U. S. Census. It could handle up to 80 characters or columns of data, a number which remains to this day as the width of most computer video display devices.}
	)
	
	(method (doVerb)
		(TimePrint 510 77)
	)
)

(instance chair of Feature
	(properties
		x 39
		y 84
		nsTop 65
		nsLeft 28
		nsBottom 103
		nsRight 51
		description {the chair}
		sightAngle 40
		lookStr {This chair is for the exclusive use of the maitre d'.}
	)
)

(instance bullBoard of Feature
	(properties
		x 25
		y 31
		nsTop 6
		nsBottom 56
		nsRight 51
		description {the bulletin board}
		sightAngle 40
		lookStr {The bulletin board is filled with photographs of famous nerds who frequent this place!}
	)
)

(instance podium of Feature
	(properties
		x 78
		y 95
		nsTop 50
		nsLeft 55
		nsBottom 95
		nsRight 102
		description {the maitre d's podium}
		sightAngle 40
		onMeCheck $0002
		lookStr {The maitre d's podium is just the right size for him to hide behind when the crowds here start throwing things!}
	)
)

(instance desk of Feature
	(properties
		x 61
		y 99
		nsTop 79
		nsLeft 46
		nsBottom 106
		nsRight 76
		description {the desk}
		sightAngle 40
		lookStr {You wish you had such a nice desk in your crummy office.}
	)
)

(instance cactus1 of Feature
	(properties
		x 203
		y 40
		nsTop 16
		nsLeft 190
		nsBottom 65
		nsRight 217
		description {the cactus}
		sightAngle 40
		onMeCheck $0004
		lookStr {The cactus is covered with needle-sharp needles. (Hence, the name "Needlenose Cacti.")}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Say ego 510 78)
			)
			(verbTalk
				(TimePrint 510 79)
			)
			(verbUse
				(switch theItem
					(iMembershipTape
						(TimePrint 510 80)
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

(instance cactus2 of Feature
	(properties
		x 116
		y 157
		nsTop 125
		nsLeft 79
		nsBottom 189
		nsRight 154
		description {the cactus}
		sightAngle 40
		onMeCheck cGREEN
	)
	
	(method (doVerb)
		(cactus1 doVerb: &rest)
	)
)

(instance musicBoxFX of Sound
	(properties
		flags mNOPAUSE
		number 510
	)
)

(instance typingFX of Sound
	(properties
		flags mNOPAUSE
		number 442
		loop -1
	)
)

(instance paperRip of Sound
	(properties
		flags mNOPAUSE
		number 512
	)
)
