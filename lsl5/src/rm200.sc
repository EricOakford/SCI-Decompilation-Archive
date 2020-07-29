;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh)
(use Main)
(use LLRoom)
(use Intrface)
(use RandCyc)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm200 0
)

(local
	roomState
	local1
	local2
	tvIsOn
	windowCued
	local5 =  5
	local6
	limoMoving
	local8
	faxIncoming
	whoseFax
	local11
	faxState
	driverState
	armState
	local15
	[local16 17] = [0 1 117 120 0 2 148 98 0 3 195 115 0 4 199 124 -32768]
	local33
	[local34 3]
)
(instance rm200 of LLRoom
	(properties
		lookStr {You've always wondered what it would be like to travel by limousine.}
		picture 200
	)
	
	(method (init &tmp temp0)
		(if (!= prevRoomNum 205)
			(theMusic number: 0 client: 0 stop:)
			(theMusic2 number: 0 client: 0 stop:)
		)
		(LoadMany VIEW 200 206)
		(HandsOn)
		(InFirstPerson 1)
		(User canControl: FALSE)
		(switch prevRoomNum
			(500
				(Bset fAfterNewYork)
			)
			(600
				(= global161 1)
			)
			(690
				(= global161 1)
				(Bset fAfterBiaz)
			)
			(700
				(Bset fAfterAtlanticCity)
			)
			(800
				(= global161 2)
			)
			(890
				(= global161 2)
				(Bset fAfterHammer)
			)
			(900
				(Bset fAfterMiami)
			)
		)
		(if playingAsLarry
			(LoadMany VIEW 204 201)
			(ego
				init:
				view: 201
				posn: 67 88 -100
				setPri: 14
				setCycle: 0
				setLoop: 0
				cycleSpeed: 6
				setCel: 0
				show:
				forceUpd:
			)
			(if (!= prevRoomNum 205)
				(HandsOff)
				(StartTimer 2 2 curRoom)
			)
			(driver init: view: 204 setLoop: 2 stopUpd:)
			(driverBody init: view: 204 setCel: 1 setLoop: 0)
			(driverEyes init: view: 204)
			(if (!= prevRoomNum 250)
				(theIconBar disable: ICON_WALK)
			else
				(= local15 1)
			)
			(driversWindow init: description: {Bobbi})
			(tv init: hide:)
		else
			(LoadMany VIEW 203 202)
			(if (OneOf prevRoomNum 800 600 425)
				(theIconBar enable: ICON_WALK)
				(= local15 1)
			else
				(theIconBar disable: ICON_WALK)
			)
			(driver init: view: 203 setLoop: 2)
			(driverBody init: view: 203 setCel: 1 setLoop: 0)
			(driverEyes init: view: 203)
			(ego
				init:
				view: 202
				setLoop: 6
				posn: 65 88 -100
				setPri: 14
				setCel: 0
				setCycle: 0
				ignoreActors: TRUE
				show:
				cycleSpeed: 6
				forceUpd:
			)
			(legs init: setScript: sLegs)
			(if (not (Btst fGotChampagne))
				(bottle init: stopUpd:)
			)
			(if (and (Btst 28) (Btst 29))
				(StartTimer 20 1 curRoom)
				(HandsOff)
				(= global157 3)
			)
			(driversWindow init: description: {Bobby})
		)
		(phone init: stopUpd:)
		(glass init: stopUpd:)
		(theTv init:)
		(rightDoor init:)
		(stereo init:)
		(bottles_Glasses init:)
		(fishTank init:)
		(tapes init:)
		(vcr init:)
		(faxMachine init:)
		(fish
			init:
			setCycle: Forward
			posn: (Random 241 304) (Random 87 106)
			setScript: sFishScript
		)
		(if
			(and
				(== currentCity NEW_YORK)
				(not (Btst fGotDayTrotter))
				(== prevRoomNum 250)
			)
			(dayTrotter init:)
		)
		(Load PICTURE 1)
		(Load SCRIPT RANDCYC)
		(super init:)
		(switch prevRoomNum
			(205
				(HandsOff)
				(= limoMoving 1)
				(curRoom drawPic: 200 SCROLLRIGHT setScript: sDrive)
			)
			(890
				(curRoom drawPic: 200 IRISOUT)
			)
			(else
				(curRoom drawPic: 200)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if local8 (Palette PALCycle 56 63 2))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (and (== ((theIconBar at: ICON_WALK) cursor?) 6) (not limoMoving))
					(curRoom newRoom: prevRoomNum)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if playingAsLarry
			(switch (++ roomState)
				(1
					(if (OneOf prevRoomNum 190 250)
						(StartTimer 2 1 self)
					else
						(Say ego 200 0 #caller self)
					)
				)
				(2
					(if (not (not (OneOf prevRoomNum 205 250)))
						(= roomState 0)
						(HandsOn)
						(User canControl: FALSE)
					else
						(StartTimer 2 1 self)
					)
				)
				(3
					(Bset fFlightAvailable)
					(driver doVerb: verbDo 7)
				)
			)
		else
			(HandsOff)
			(Say ego 200 1)
			(= limoDestination 425)
			(curRoom setScript: sDream)
		)
	)
	
	(method (newRoom n)
		(if (== n 205)
			(theMusic fade: 0 15 10 1)
		else
			(theMusic stop:)
			(= limoDestination 0)
		)
		(theMusic2 stop:)
		(tvSnd stop: dispose:)
		(InFirstPerson 0)
		(globalTimer dispose: delete:)
		(super newRoom: n)
	)
	
	(method (notify param1)
		(= faxIncoming 0)
		(switch param1
			(2779 (= faxIncoming TRUE))
			(-1 (TimePrint 200 2))
			(else  (TimePrint 200 3))
		)
	)
)

(instance sFishScript of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (< gameState 3)
					(fish setLoop: 1)
				else
					(fish setLoop: 3)
				)
				(if (not playingAsLarry)
					(self changeState: 10)
				else
					(= cycles 1)
				)
			)
			(1
				(= start state)
				(= cycles 1)
			)
			(2
				(if (< (fish x?) 262)
					(if (< gameState 3)
						(fish setLoop: 1)
					else
						(fish setLoop: 3)
					)
					(fish
						setMotion: MoveTo (Random 270 304) (Random 87 106) self
					)
				else
					(if (< gameState 3)
						(fish setLoop: 0)
					else
						(fish setLoop: 2)
					)
					(fish
						setMotion: MoveTo (Random 241 250) (Random 87 106) self
					)
				)
			)
			(3 (= seconds 2))
			(4 (self init:))
			(10
				(= start state)
				(= cycles 1)
			)
			(11
				(if (< (fish x?) 262)
					(= register 1)
					(fish
						setLoop: 1
						setMotion: MoveTo (Random 270 304) (Random 87 106) self
					)
				else
					(= register 0)
					(fish
						setLoop: 0
						setMotion: MoveTo (Random 241 250) (Random 87 106) self
					)
				)
			)
			(12
				(if (not (Random 0 2))
					(if register (fish setLoop: 3) else (fish setLoop: 2))
					(= seconds (Random 1 3))
				else
					(= seconds 1)
				)
			)
			(13 (self init:))
		)
	)
)

(instance fish of Actor
	(properties
		x 245
		y 100
		description {the fish}
		sightAngle 40
		lookStr {There's a carsick goldfish in the limousine's aquarium.}
		view 206
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 200 4)
			)
			(verbTalk
				(TimePrint 200 5)
			)
			(verbZipper
				(TimePrint 200 6)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance glass of Actor
	(properties
		x 156
		y 38
		description {the privacy window}
		sightAngle 40
		lookStr {A piece of soundproof glass blocks conversation between you and your driver.}
		yStep 4
		view 200
		cel 3
		priority 3
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		illegalBits $0000
		xStep 4
	)
	
	(method (cue)
		(super cue:)
		(if (not windowCued)
			(= windowCued TRUE)
		else
			(= windowCued FALSE)
		)
		(self stopUpd:)
		(HandsOn)
		(User canControl: FALSE)
		(if
			(or
				limoMoving
				(not local15)
				(not (== ((theIconBar at: ICON_WALK) cursor?) 6))
			)
			(theIconBar disable: ICON_WALK)
		)
	)
)

(instance dayTrotter of View
	(properties
		x 8
		y 187
		description {the DayTrotter\05}
		view 200
		cel 1
		priority 4
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 200 7)
				(= local11 1)
			)
			(verbDo
				(HandsOff)
				(arm
					init:
					view: 201
					setLoop: 2
					setCel: 0
					posn: 67 164
					setCycle: CycleTo 2 1 arm
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance legs of Prop
	(properties
		x 73
		y 181
		description {your leg}
		lookStr {You've always felt your legs were one of your better assets.}
		view 202
		loop 3
		priority 13
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance theTv of Feature
	(properties
		x 161
		y 91
		nsTop 64
		nsLeft 117
		nsBottom 118
		nsRight 205
		description {the television set}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if tvIsOn
					(TimePrint 200 8)
				else
					(TimePrint 200 9)
					(TimePrint 200 10 67 -1 185)
				)
			)
			(verbDo
				(if playingAsLarry
					(tvSnd number: 201 loop: 1 play:)
					(if tvIsOn
						(= tvIsOn FALSE)
						(tv hide:)
						(TimePrint 200 11)
					else
						(= tvIsOn TRUE)
						(tv show:)
						(TimePrint 200 12)
					)
				else
					(TimePrint 200 13)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance tv of Prop
	(properties
		x 162
		y 110
		nsTop 63
		nsLeft 116
		nsBottom 118
		nsRight 206
		description {the television set}
		sightAngle 40
		view 205
		priority 7
	)
	
	(method (doit)
		(super doit:)
		(if tvIsOn
			(tv setCel: (Random 0 3))
		)
	)
	
	(method (doVerb)
		(theTv doVerb: &rest)
	)
)

(instance phone of Prop
	(properties
		x 161
		y 271
		z 100
		nsTop 162
		nsLeft 143
		nsBottom 174
		nsRight 186
		description {the telephone}
		sightAngle 40
		view 200
		loop 1
		priority 10
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (TimePrint 200 14))
			(3 (self setScript: sPhone))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance dreamProp1 of Prop
	(properties
		view 231
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance dreamProp2 of Prop
	(properties
		view 231
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance dreamProp3 of Prop
	(properties
		view 231
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance arm of Prop
	(properties
		view 201
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (cue)
		(super cue:)
		(switch (++ armState)
			(1
				(if (not local1) (SolvePuzzle 12))
				(ego get: iDayTrotter)
				(Bset fGotDayTrotter)
				(dayTrotter dispose:)
				(StartTimer 2 1 self)
			)
			(2
				(self setCycle: BegLoop self)
			)
			(3
				(= armState 0)
				(if (not local11)
					(TimePrint 200 7)
				)
				(TimePrint 200 15)
				(if (not local1)
					(HandsOn)
					(User canControl: FALSE)
					(if
						(or
							limoMoving
							(not local15)
							(not (== ((theIconBar at: 0) cursor?) 6))
						)
						(theIconBar disable: ICON_WALK)
						(self dispose:)
					)
				else
					(curRoom newRoom: limoDestination)
				)
			)
		)
	)
)

(instance bottle of View
	(properties
		x 41
		y 111
		description {the champagne bottle}
		sightAngle 40
		view 200
		priority 5
		signal (| fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 200 16)
			)
			(verbDo
				(HandsOff)
				(Bset fGotChampagne)
				(ego setScript: sGetBottle)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance faxPaper of Actor
	(properties
		x 55
		y 225
		z 100
		description {the fax}
		yStep 1
		view 200
		cel 2
		priority 6
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		xStep 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (== (User canInput:) TRUE)
					(HandsOff)
				)
				(ego view: 202 setLoop: 1 setCel: 0 setCycle: CycleTo 6 1 self)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(switch (++ faxState)
			(1
				(HandsOff)
				(self hide:)
				(ego setCycle: EndLoop self)
			)
			(2
				(ego setLoop: 6)
				(StartTimer 2 1 self)
			)
			(3
				(switch whoseFax
					(1
						(SolvePuzzle 13 fGotBiazFax)
						(TimePrint 200 17)
						(ego get: iBiazFax)
					)
					(2
						(SolvePuzzle 13 fGotHammerFax)
						(TimePrint 200 18)
						(ego get: iHammerFax)
					)
				)
				(= faxState 0)
				(= whoseFax 0)
				(HandsOn)
				(User canControl: FALSE)
				(if
					(or
						limoMoving
						(not local15)
						(not (== ((theIconBar at: ICON_WALK) cursor?) 6))
					)
					(theIconBar disable: ICON_WALK)
				)
				(self dispose:)
			)
		)
	)
)

(instance vcr of Feature
	(properties
		x 162
		y 152
		z 100
		nsTop 45
		nsLeft 114
		nsBottom 60
		nsRight 211
		description {the video recorder}
		sightAngle 40
		lookStr {A professional quality videotape player rests above the television set.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 200 19)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance tapes of Feature
	(properties
		x 245
		y 257
		z 200
		nsTop 45
		nsLeft 211
		nsBottom 70
		nsRight 279
		description {the video tapes}
		sightAngle 40
		lookStr {There are many videotapes in that rack that you would love to see. Unfortunately, the VCR is broken!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 200 20)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fishTank of Feature
	(properties
		x 266
		y 94
		nsTop 71
		nsLeft 217
		nsBottom 118
		nsRight 316
		description {the aquarium}
		sightAngle 40
		lookStr {How bizarre! A limousine with an aquarium!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (not (Random 0 10))
					(TimePrint 200 21)
				else
					(TimePrint 200 4)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance dreamBubble of Prop
	(properties
		view 207
		priority 15
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance bottles_Glasses of Feature
	(properties
		x 73
		y 178
		z 100
		nsTop 46
		nsLeft 31
		nsBottom 111
		nsRight 115
		description {the bottles and glasses}
		sightAngle 40
		onMeCheck cGREEN
		lookStr {There are enough bottles of liquor and glasses to throw a party. All you need now is some friends!}
		name "bottles&Glasses"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (cast contains: bottle)
					(bottle doVerb: verbDo)
				else
					(TimePrint 200 22)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance stereo of Feature
	(properties
		x 165
		y 248
		z 100
		nsTop 117
		nsLeft 110
		nsBottom 178
		nsRight 222
		description {the stereo}
		sightAngle 40
		lookStr {The limousine's stereo has both a cassette tape player and a CD.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 200 23)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rightDoor of Feature
	(properties
		x 286
		y 146
		nsTop 123
		nsLeft 253
		nsBottom 170
		nsRight 319
		description {the cabinet door}
		sightAngle 40
		onMeCheck cBLUE
		lookStr {A small, locked liquor compartment rests beneath the right front passenger's seat. You wonder when it will be your turn to live high on the hog.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 200 24)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sRoll of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(dreamProp1 setLoop: (Random 0 1) setCycle: EndLoop self)
			)
			(1 (= cycles (Random 4 10)))
			(2
				(dreamProp1 setCycle: BegLoop self)
			)
			(3 (= cycles (Random 4 10)))
			(4 (self init:))
		)
	)
)

(instance sPhone of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(Load SOUND 36)
				(= local6 1)
				(if playingAsLarry
					(ego cycleSpeed: 8 setCycle: CycleTo 5 1 self)
					(= register 5)
				else
					(ego stopUpd:)
					(arm
						init:
						view: 202
						posn: 65 188
						setLoop: 0
						cycleSpeed: 8
						setCycle: CycleTo 3 1 self
					)
					(legs setScript: 0 setCel: 0 stopUpd:)
				)
			)
			(1
				(phone hide:)
				(theMusic2 number: 36 loop: 1 play:)
				(if playingAsLarry
					(ego setCycle: EndLoop self)
				else
					(arm setCycle: EndLoop self)
				)
			)
			(2
				(cond 
					(playingAsLarry
						(TimePrint 200 25 #title {Bobbi})
						(TimePrint 200 26 #title {Bobbi})
						(Say ego 200 27)
						(++ state)
						(= cycles 2)
					)
					((and (Btst fGotBiazFax) (Btst fGotHammerFax))
						(User canInput: TRUE)
						((ScriptID DIALER 0) init: 1 self)
					)
					(faxIncoming
						(TimePrint 200 28)
						(++ state)
						(= cycles 2)
					)
					((not whoseFax)
						(User canInput: TRUE)
						((ScriptID DIALER 0) init: 1 self)
					)
					(else
						(TimePrint 200 29)
						(++ state)
						(= cycles 2)
					)
				)
			)
			(3
				(theIconBar curIcon: (theIconBar at: ICON_DO))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(if faxIncoming
					(TimePrint 200 30)
					(TimePrint 200 31 #title {Desmond})
					(cond 
						((and (Btst fGotBiazFax) (Btst fGotHammerFax))
							(Say ego 200 32)
							(TimePrint 200 33 #title {Desmond})
							(Say ego 200 34)
						)
						((and (not (Btst fGotBiazFax)) (not (Btst fGotHammerFax)))
							(Say ego 200 35)
							(TimePrint 200 36 #title {Desmond})
							(= whoseFax
								(Print 200 37
									#button {ReverseBiaz} 1
									#button {PCHammer} 2
									#title {You}
								)
							)
						)
						((Btst fGotBiazFax)
							(Say ego 200 38)
							(TimePrint 200 39 #title {Desmond})
							(= whoseFax 2)
						)
						((Btst fGotHammerFax)
							(Say ego 200 40)
							(TimePrint 200 39 #title {Desmond})
							(= whoseFax 1)
						)
					)
					(if whoseFax
						(faxPaper init: hide: setScript: sFax)
					)
				)
				(= ticks 50)
			)
			(4
				(if playingAsLarry
					(ego setCycle: CycleTo 5 -1 self)
				else
					(arm setCycle: CycleTo 3 -1 self)
				)
			)
			(5
				(phone show:)
				(theMusic2 number: 36 loop: 1 play:)
				(if playingAsLarry
					(ego setCycle: BegLoop self)
				else
					(arm setCycle: BegLoop self)
				)
			)
			(6
				(if
					(or
						limoMoving
						(not local15)
						(not (== ((theIconBar at: ICON_WALK) cursor?) 6))
					)
					(theIconBar disable: ICON_WALK)
				)
				(if (not playingAsLarry)
					(legs startUpd: setScript: sLegs)
					(arm dispose:)
				)
				(= local6 0)
				(= cycles 2)
			)
			(7
				(HandsOn)
				(User canControl: FALSE)
				(if (not local15)
					(theIconBar disable: ICON_WALK)
				)
				(self dispose:)
			)
		)
	)
)

(instance driverEyes of Prop
	(properties
		x 155
		y 6
		description {the mirror}
		view 204
		loop 1
		priority 1
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance sLegs of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(legs setCycle: Forward cycleSpeed: (Random 6 10))
				(= cycles (Random 20 40))
			)
			(2
				(legs setCel: 0 stopUpd:)
				(= cycles (Random 10 30))
			)
			(3 (self init:))
		)
	)
)

(instance windowL of Prop
	(properties
		x 17
		y 46
		description {the window}
		view 208
		priority 14
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 200 41)
			)
			(verbLook
				(if limoMoving
					(TimePrint 200 42)
				else
					(TimePrint 200 43)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance tvSnd of Sound)

(instance faxMachine of Feature
	(properties
		x 59
		y 213
		z 100
		nsTop 101
		nsLeft 31
		nsBottom 125
		nsRight 87
		description {the fax machine}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 200 44)
				(TimePrint 200 45 #at -1 185)
			)
			(verbDo
				(if (and whoseFax (not faxIncoming))
					(HandsOff)
					(faxPaper doVerb: verbDo)
				else
					(TimePrint 200 46)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sFax of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 4 6)))
			(1
				(HandsOff)
				(Load SOUND 203)
				(= cycles 2)
			)
			(2
				(theMusic number: 203 loop: 1 play: self)
				(TimePrint 200 47)
			)
			(3
				(faxPaper
					show:
					ignoreActors: TRUE
					moveSpeed: 5
					posn: 55 225
					setMotion: MoveTo 55 215 self
				)
			)
			(4
				(= faxIncoming FALSE)
				(HandsOn)
				(User canControl: FALSE)
				(if
					(or
						limoMoving
						(not local15)
						(not (== ((theIconBar at: ICON_WALK) cursor?) 6))
					)
					(theIconBar disable: ICON_WALK)
				)
				(self dispose:)
			)
		)
	)
)

(instance driverBody of Prop
	(properties
		x 114
		y 34
		description {your limo driver}
		view 204
		priority 1
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance driversWindow of Feature
	(properties
		x 160
		y 119
		z 100
		nsTop -1
		nsLeft 49
		nsBottom 42
		nsRight 269
		sightAngle 40
		lookStr {You are sealed off from your driver by that big soundproof glass window.}
	)
	
	(method (doVerb)
		(driver doVerb: &rest)
	)
)

(instance sGetBottle of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 202
					setLoop: 1
					setCel: 0
					cycleSpeed: 6
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(ego setLoop: 2 setCel: 0)
				(= cycles 1)
			)
			(2
				(ego cycleSpeed: 6 setCycle: CycleTo 3 1 self)
			)
			(3
				(bottle dispose:)
				(ego setCycle: EndLoop self)
			)
			(4
				(ego setLoop: 6 setCel: 0 posn: 65 88 get: iChampagne)
				(if
					(or
						limoMoving
						(not local15)
						(not (== ((theIconBar at: ICON_WALK) cursor?) 6))
					)
					(theIconBar disable: ICON_WALK)
				)
				(SolvePuzzle 6)
				(= cycles 2)
			)
			(5
				(HandsOn)
				(User canControl: FALSE)
				(if (not local15)
					(theIconBar disable: FALSE)
				)
				(self dispose:)
			)
		)
	)
)

(instance sDrive of Script

	(method (doit &tmp [temp0 10])
		(super doit:)
		(if (not (-- local5))
			(driverBody setCel: (Random 0 2))
			(= local5 (Random 5 10))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= limoMoving TRUE)
				(if playingAsLarry
					(HandsOn)
					(User canControl: FALSE)
					(theIconBar disable: ICON_WALK)
				)
				(theMusic number: 204 loop: -1 play: 127)
				(if (== prevRoomNum 205)
					(windowL init: cycleSpeed: 3 setCycle: Forward)
					(= state 29)
					(= seconds 3)
				else
					(windowL init: cycleSpeed: 6 setCycle: Forward)
					(= seconds 5)
				)
			)
			(1
				(windowL cycleSpeed: 3)
				(= seconds 5)
			)
			(2
				(windowL cycleSpeed: 0)
				(if playingAsLarry
					(= seconds 4)
				else
					(= seconds 12)
				)
			)
			(3
				(= start state)
				(= cycles 2)
			)
			(4
				(cond 
					(local6
						(self init:)
					)
					((and (not playingAsLarry) (not local33))
						(self init:)
					)
					((and playingAsLarry (not (OneOf prevRoomNum 205 250)))
						(curRoom newRoom: 205)
					)
					(else
						(= seconds 3)
					)
				)
			)
			(5
				(windowL cycleSpeed: 3)
				(= seconds 3)
			)
			(6
				(= start state)
				(= cycles 1)
			)
			(7
				(if local6
					(self init:)
				else
					(HandsOff)
					(driver setCycle: EndLoop)
					(driverEyes setCycle: EndLoop)
					(= state 29)
					(= cycles 2)
				)
			)
			(30
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(theMusic fade: 0 15 10 0)
				(if playingAsLarry
					(TimePrint 200 48 #title {Bobbi})
					(= cycles 2)
				else
					(++ state)
					(theMusic2 fade: 0 15 10 1)
					(= seconds 2)
				)
			)
			(31
				(Bclr fLimoHere)
				(if (!= limoDestination 250)
					(Bclr fLimoAvailable)
				)
				(if (and (== limoDestination 900) (ego has: iChange))
					(Say ego 200 49)
					(ego put: iChange)
					(= numQuarters 0)
				)
				(if (and (== limoDestination 500) (not (ego has: iDayTrotter)))
					(TimePrint 200 50)
					(= local1 1)
					(dayTrotter doVerb: verbDo &rest)
				else
					(if (and (Btst fAfterNewYork) (ego has: iDayTrotter))
						(ego put: iDayTrotter put: iCreditCards put: iMoney)
						(TimePrint 200 51)
					)
					(curRoom newRoom: limoDestination)
				)
			)
			(32
				(if modelessDialog (modelessDialog dispose:))
				(cast eachElementDo: #hide)
				(curRoom drawPic: 1 FADEOUT)
				(DoDisplay 2 myDisplayColor 200 52)
				(= seconds 3)
			)
			(33
				(switch limoDestination
					(425 0)
					(800 (= global161 2))
					(600 (= global161 1))
				)
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance driver of Prop
	(properties
		x 114
		y 34
		description {your limo driver}
		view 204
		priority 2
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem &tmp [str 80])
		(switch theVerb
			(verbLook
				(if playingAsLarry
					(switch currentCity
						(LOS_ANGELES
							(TimePrint 200 59)
						)
						(NEW_YORK
							(TimePrint 200 60)
						)
						(ATLANTIC_CITY
							(TimePrint 200 61)
						)
						(MIAMI
							(TimePrint 200 62)
						)
					)
				else
					(TimePrint 200 63)
				)
			)
			(verbDo
				(cond 
					(limoMoving
						(TimePrint 200 64)
					)
					(playingAsLarry
						(TimePrint 200 65)
					)
					(else
						(HandsOff)
						(theMusic number: 202 loop: 1 play:)
						(if (not windowCued)
							(glass setMotion: MoveTo (glass x?) 78 glass)
						else
							(glass setMotion: MoveTo (glass x?) 38 glass)
						)
					)
				)
			)
			(verbTalk
				(cond 
					(limoMoving
						(Format @str 200 66
							(if playingAsLarry {Sh} else {H})
							(if playingAsLarry {sh} else {h})
						)
						(TimePrint @str)
					)
					(playingAsLarry
						(switch (Random 1 2)
							(1 (TimePrint 200 67))
							(2 (TimePrint 200 68))
						)
						(TimePrint 200 69)
					)
					(windowCued (TimePrint 200 70 #title {Bobby}))
					(else (TimePrint 200 71))
				)
			)
			(10
				(cond 
					(playingAsLarry
						(Say ego 200 72)
						(TimePrint 200 73)
						(TimePrint 200 74 #at -1 185)
					)
					((Btst fAfterBiaz)
						(TimePrint 200 75 #title {Bobby})
					)
					(limoMoving
						(TimePrint 200 76 #title {Bobby})
					)
					(else
						(TimePrint 200 77 #title {Bobby})
					)
				)
			)
			(verbUse
				(if playingAsLarry
					(if limoDestination
						(TimePrint 200 78 #title {Bobbi})
					else
						(switch theItem
							(iGoldCard
								(if (== prevRoomNum 250)
									(TimePrint 200 79 #title {Bobbi})
								else
									(arm
										init:
										view: 201
										setLoop: 1
										posn: 96 163
										setCel: 0
										cycleSpeed: 8
										setCycle: EndLoop self
									)
									(= limoDestination 250)
								)
							)
							(iNapkin
								(cond 
									((!= currentCity NEW_YORK)
										(TimePrint 200 80 #title {Bobbi})
									)
									((not (OneOf prevRoomNum 205 250))
										(TimePrint 200 81 #title {Bobbi})
									)
									((Btst fAfterNewYork)
										(TimePrint 200 82 #title {Bobbi})
									)
									(else
										(arm
											init:
											view: 201
											setLoop: 1
											posn: 96 163
											setCel: 0
											setCycle: EndLoop self
										)
										(= limoDestination 500)
									)
								)
							)
							(iMatchbook
								(cond 
									((!= currentCity ATLANTIC_CITY)
										(TimePrint 200 83 #title {Bobbi})
									)
									((not (OneOf prevRoomNum 205 250))
										(TimePrint 200 81 #title {Bobbi})
									)
									((Btst fAfterAtlanticCity)
										(TimePrint 200 84)
									)
									(else
										(arm
											init:
											view: 201
											setLoop: 1
											posn: 96 163
											setCel: 0
											setCycle: EndLoop self
										)
										(= limoDestination 700)
									)
								)
							)
							(iPulliamCard
								(cond 
									((!= currentCity MIAMI)
										(TimePrint 200 85 #title {Bobbi})
									)
									((not (OneOf prevRoomNum 205 250))
										(TimePrint 200 81 #title {Bobbi})
									)
									((Btst fAfterMiami)
										(TimePrint 200 86)
									)
									(else
										(arm
											init:
											view: 201
											setLoop: 1
											posn: 96 163
											setCel: 0
											setCycle: EndLoop self
										)
										(= limoDestination 900)
									)
								)
							)
							(else 
								(super doVerb: theVerb theItem &rest)
							)
						)
					)
				else
					(switch theItem
						(iBiazFax
							(cond 
								((== global161 1)
									(TimePrint 200 87 #title {Bobby})
								)
								((Btst fAfterBiaz)
									(TimePrint 200 88 #title {Bobby})
								)
								(limoDestination
									(TimePrint 200 89 #title {Bobby})
								)
								(else
									(SolvePuzzle 8 fPattiTakesRide)
									(arm
										view: 202
										init:
										setLoop: 4
										setCel: 0
										posn: 98 182
										setCycle: EndLoop self
									)
									(+= global157 1)
									(= limoDestination 600)
									(= faxIncoming FALSE)
									(faxPaper setScript: 0)
								)
							)
						)
						(iHammerFax
							(cond 
								((== global161 2)
									(TimePrint 200 87 #title {Bobby})
								)
								((Btst fAfterHammer)
									(TimePrint 200 88 #title {Bobby})
								)
								(limoDestination
									(TimePrint 200 89 #title {Bobby})
								)
								(else
									(SolvePuzzle 8 fPattiTakesRide)
									(arm
										view: 202
										init:
										setLoop: 4
										setCel: 0
										posn: 98 182
										setCycle: EndLoop self
									)
									(+= global157 2)
									(= limoDestination 800)
									(= faxIncoming FALSE)
									(faxPaper setScript: 0)
								)
							)
						)
						(iDataMan
							(switch ((Inventory at: iCamcorder) state?)
								(datamanEMPTY
									(TimePrint 200 90)
								)
								(datamanBIAZ
									(cond 
										((== global161 1)
											(TimePrint 200 87 #title {Bobby})
										)
										((Btst fAfterBiaz)
											(TimePrint 200 88 #title {Bobby})
										)
										(limoDestination
											(TimePrint 200 89 #title {Bobby})
										)
										(else
											(SolvePuzzle 8 fPattiTakesRide)
											(arm
												view: 202
												init:
												setLoop: 5
												setCel: 0
												posn: 96 184
												setCycle: EndLoop self
											)
											(+= global157 1)
											(= limoDestination 600)
											(= faxIncoming FALSE)
											(faxPaper setScript: 0)
										)
									)
								)
								(datamanHAMMER
									(cond 
										((== global161 2)
											(TimePrint 200 87 #title {Bobby})
										)
										((Btst fAfterHammer)
											(TimePrint 200 88 #title {Bobby})
										)
										(limoDestination
											(TimePrint 200 89 #title {Bobby})
										)
										(else
											(SolvePuzzle 8 fPattiTakesRide)
											(arm
												view: 202
												init:
												setLoop: 5
												setCel: 0
												posn: 96 184
												setCycle: EndLoop self
											)
											(+= global157 2)
											(= limoDestination 800)
											(= faxIncoming FALSE)
											(faxPaper setScript: 0)
										)
									)
								)
							)
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
		(switch (++ driverState)
			(1
				(HandsOff)
				(driver setCycle: EndLoop)
				(driverEyes setCycle: EndLoop)
				(StartTimer 2 0 self)
			)
			(2
				(switch limoDestination
					(250
						(TimePrint 200 53 #title {Bobbi})
						(StartTimer 2 1 self)
					)
					(500
						(TimePrint 200 54 #title {Bobbi})
						(StartTimer 2 1 self)
					)
					(700
						(TimePrint 200 55 #title {Bobbi})
						(StartTimer 2 1 self)
					)
					(900
						(TimePrint 200 56 #title {Bobbi})
						(StartTimer 2 1 self)
					)
					(600
						(TimePrint 200 57 #title {Bobby})
						(if (== prevRoomNum 800) (Bset 28))
						(StartTimer 2 1 self)
					)
					(800
						(TimePrint 200 58 #title {Bobby})
						(if (== prevRoomNum 600) (Bset 29))
						(StartTimer 2 1 self)
					)
				)
			)
			(3
				(driver setCycle: BegLoop)
				(driverEyes setCycle: BegLoop)
				(arm setCycle: BegLoop self)
			)
			(4
				(= driverState 0)
				(arm dispose:)
				(if playingAsLarry
					(curRoom setScript: sDrive)
				else
					(curRoom setScript: sDream)
				)
			)
		)
	)
)

(instance sDream of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(driver setScript: sDrive)
				(legs setScript: 0 setCel: 0)
				(= seconds 8)
			)
			(1
				(= local8 1)
				(theMusic fade: 0 15 5 0)
				(theMusic2 stop:)
				(= seconds 2)
			)
			(2
				(theMusic2 number: 320 loop: -1 play: 0 fade: 127 15 10 0)
				(TimePrint 200 91 #at -1 185 #dispose)
				(tv dispose:)
				(fish setScript: 0 stopUpd:)
				(dreamBubble init: setLoop: 0 setCel: 1 posn: 117 120)
				(= ticks 90)
			)
			(3
				(dreamBubble setCel: 2 posn: 148 98)
				(= ticks 90)
			)
			(4
				(dreamBubble setCel: 3 posn: 195 115)
				(= ticks 90)
			)
			(5
				(dreamBubble setCel: 4 posn: 199 124)
				(= ticks 90)
			)
			(6
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(dreamBubble dispose:)
				(fish hide:)
				(bottle hide:)
				(tv hide:)
				(switch (++ pattiDreamNum)
					(1
						(curRoom overlay: 230)
						(dreamProp1
							init:
							view: 231
							setLoop: 0
							posn: 205 90
							setScript: sRoll
						)
					)
					(2
						(curRoom overlay: 235)
						(dreamProp1
							init:
							view: 235
							posn: 123 69
							cycleSpeed: 6
							setCycle: RandCycle
						)
						(dreamProp2
							init:
							view: 236
							posn: 231 87
							cycleSpeed: 8
							setCycle: RandCycle
						)
					)
					(3
						(curRoom overlay: 240)
						(theMusic2 number: 241 play:)
						(dreamProp1
							init:
							view: 240
							setLoop: 0
							setCel: 0
							cycleSpeed: 8
							setCycle: Forward
							posn: 258 93
							setPri: 13
						)
						(dreamProp2
							init:
							view: 241
							setLoop: 0
							posn: 260 106
							setPri: 12
							cycleSpeed: 10
							setCycle: RandCycle
						)
						(dreamProp3
							init:
							view: 241
							setLoop: 1
							setCel: 0
							cycleSpeed: 5
							setCycle: EndLoop
							posn: 203 31
							setPri: 14
						)
					)
				)
				(= seconds 3)
			)
			(7
				(switch pattiDreamNum
					(1
						(Say ego 200 92)
						(TimePrint 200 93 #title {Donald})
						(Say ego 200 94)
						(TimePrint 200 95 #title {The Donald})
					)
					(2
						(Say ego 200 96)
						(TimePrint 200 97 #title {Willie})
					)
					(3
						(Say ego 200 98)
						(TimePrint 200 99 #title {Sir Francis})
					)
				)
				(= local33 1)
			)
		)
	)
)
