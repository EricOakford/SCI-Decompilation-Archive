;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmOutside7_11) ;500
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm500 0
	aBum 1
)

(local
	bumDone
	bumLoop
	local2
	interactedWithBum
	usedItemOnBum
	[local5 2]
	phoneRings
	[local8 8]
)
(instance rm500 of LLRoom
	(properties
		lookStr {You are outside Lost Wages' one and only Quiki-Mart Convenience Store. A telephone hangs from the utility pole, minus its directory.}
		picture rmOutside7_11
		north rmInside7_11
		east rmOutsideDisco
		west rmDarkAlley
	)
	
	(method (init)
		(LoadMany VIEW 501 808)
		(LoadMany SOUND 507 504)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 172 149 215 149 215 152 172 152
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 319 135 270 135 258 147 81 147 81 137 129 131 319 4
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 68 140 42 140 35 112 0 112 0 0 76 0 76 136
					yourself:
				)
		)
		(fTelephone init: approachVerbs: verbDo verbUse verbZipper verbTaste verbTalk verbLook)
		(fEntryLights init:)
		(fLight init:)
		(street init:)
		(darkAlley init:)
		(fWindow init:)
		(fArtGallery init:)
		(fSign init:)
		(switch prevRoomNum
			(rmOutsideDisco
				(self style: SCROLLRIGHT)
				(if (< (ego y?) 135) (ego y: 135))
			)
			(rmInside7_11
				(HandsOff)
				(curRoom setScript: sFromStore)
			)
			(rmKeypad
				(ego
					moveHead: FALSE
					normal: FALSE
					view: 501
					setLoop: 0
					setCel: 5
					x: 172
					y: 150
				)
				(HandsOff)
				(if debugging (Bset fHeardSurveyFirstTime))
				(if (Btst fHeardSurveyFirstTime)
					(curRoom setScript: sHot_Bothered)
				else
					(curRoom setScript: sFromTelephone)
				)
			)
			(rmDarkAlley (ego y: 145))
			(rmInsideTaxi 0)
			(else  (ego posn: 160 150))
		)
		(ego init:)
		(super init:)
		(if
			(or
				debugging
				(and
					(CheckItemOwner iPocketKnife)
					(> (ego x?) 50)
					(!= prevRoomNum 505)
				)
			)
			(if (Btst fNearBum)
				(aBum setScript: sBumBegs)
				(Load SOUND 111 120)
				(aBum cycleSpeed: howFast moveSpeed: (+ 1 howFast) init:)
				(aBumHead cycleSpeed: (+ 3 howFast) init:)
			)
			(Btoggle fNearBum)
		)
		(self setRegions: rgSidewalk)
		(if (== debugging 2) (Bset fAnsweredSurvey))
		(if (and (!= prevRoomNum rmKeypad) (Btst fAnsweredSurvey))
			(sfxPhoneRinging prevSignal: 0)
			(sfxPhoneRinging play:)
			(Bset fPhoneRinging)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(return
			(cond 
				(script)
				((< (ego y?) 125) (curRoom newRoom: rmDarkAlley))
				((IsObjectOnControl ego cBLUE) (HandsOff) (curRoom setScript: sToStore))
				((== (sfxPhoneRinging prevSignal?) 1)
					(switch phoneRings
						(0
							(if modelessDialog (modelessDialog dispose:))
							(Print 500 0 #time 3 #dispose #at -1 20)
						)
						(1
							(if modelessDialog (modelessDialog dispose:))
							(Print 500 1 #time 3 #dispose #at -1 20)
						)
						(2
							(if modelessDialog (modelessDialog dispose:))
							(Print 500 2 #time 3 #dispose #at -1 20)
						)
						(3
							(if modelessDialog (modelessDialog dispose:))
							(Print 500 3 #time 3 #dispose #at -1 20)
						)
						(4
							(if modelessDialog (modelessDialog dispose:))
							(Print 500 4 #time 4 #dispose #at -1 20)
						)
					)
					(sfxPhoneRinging prevSignal: 0)
					(if (== phoneRings 8) (= phoneRings 4) else (++ phoneRings))
				)
			)
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(super dispose:)
	)
)

(instance sFromStore of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 99 131 setMotion: MoveTo 82 138 self)
			)
			(1
				(ego setMotion: MoveTo 79 148 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance sToStore of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 85 134 self)
			)
			(1
				(ego setMotion: PolyPath 118 130 self)
			)
			(2 (curRoom newRoom: rmInside7_11))
		)
	)
)

(instance sBumBegs of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
			(and (== state 1) (< (aBum distanceTo: ego) 45)) (= cycles 1))
			(
				(or
					(and (== state 4) (ego mover?))
					(== (curRoom script?) (ScriptID 813 1))
					(== (ego view?) 806)
				)
				(= state 7)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aBum setCycle: Walk setMotion: MoveTo 0 158 self)
			)
			(1
				(theMusic number: 120 setLoop: 1 flags: mNOPAUSE play: 127)
				(aBum setCycle: Walk setMotion: MoveTo 160 158 self)
			)
			(2
				(HandsOff)
				(Face ego aBum)
				(aBum setMotion: 0)
				(= cycles 1)
			)
			(3
				(= local2 1)
				(aBum setMotion: 0 setLoop: 4 setCycle: EndLoop)
				(aBumHead
					cycleSpeed: (+ 3 howFast)
					setCycle: RandCycle 7 self
				)
			)
			(4
				(if (not (curRoom script?)) (HandsOn))
				(Print 500 5 #time 5)
				(= seconds 5)
			)
			(5
				(aBumHead setCycle: RandCycle 10 self)
			)
			(6
				(Print 500 6 #time 5)
				(= seconds 5)
			)
			(7
				(aBum setLoop: 4 setCycle: BegLoop)
				(aBumHead setCycle: RandCycle 10 self)
			)
			(8
				(Print 500 7 #time 5)
				(= seconds 5)
			)
			(9 (aBum setScript: sBumLeaves))
		)
	)
)

(instance sGetKnife of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (<= (aBum x?) 60) (== (aBum loop?) 1))
					(Print 500 8)
					(self dispose:)
				)
				(= cycles 1)
			)
			(1
				(HandsOff)
				(cond 
					((<= (ego x?) (aBum x?)) (= bumLoop 1) (aBum view: 502 setMotion: 0 setLoop: 1))
					((> (ego x?) (aBum x?)) (aBum view: 502 setMotion: 0 setLoop: 0))
				)
				(if (> (aBum distanceTo: ego) 45)
					(if (<= (ego x?) (aBum x?))
						(ego
							setMotion: PolyPath (- (aBum x?) 45) (aBum y?) self
						)
					else
						(ego
							setMotion: PolyPath (+ (aBum x?) 45) (aBum y?) self
						)
					)
				else
					(= cycles 1)
				)
			)
			(2
				(Face ego aBum)
				(= cycles 3)
			)
			(3
				(ego put: iWine)
				(aBum
					setLoop: (+ 4 (mod (aBum loop?) 2))
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(aBum
					setLoop: (+ 6 (mod (aBum loop?) 2))
					setCycle: EndLoop self
				)
			)
			(5
				(sfxGlug play: self)
				(Print 500 9 #at -1 20 #dispose)
				(aBum
					setLoop: (+ 8 (mod (aBum loop?) 2))
					setCycle: ForwardCounter
				)
			)
			(6
				(if modelessDialog (modelessDialog dispose:))
				(aBum
					setLoop: (+ 6 (mod (aBum loop?) 2))
					setCel: 3
					setCycle: BegLoop self
				)
			)
			(7
				(aBum setLoop: bumLoop)
				(= cycles 1)
			)
			(8
				(aBum setLoop: (+ 4 (mod (aBum loop?) 2)) setCel: 0)
				(= cycles 1)
			)
			(9
				(aBumHead
					cycleSpeed: (+ 4 howFast)
					setCycle: RandCycle 20 self
				)
			)
			(10
				(Print 500 10)
				(= cycles 1)
			)
			(11 (aBum setCycle: BegLoop self))
			(12
				(ego get: iPocketKnife)
				(SolvePuzzle fGetKnife 5)
				(Print 500 11)
				(= cycles 1)
			)
			(13
				(if (not (curRoom script?)) (HandsOn))
				(aBum setScript: sBumLeaves)
			)
		)
	)
)

(instance sBumInteraction of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (<= (aBum x?) 60) (== (aBum loop?) 1))
					(Print 500 8)
					(self dispose:)
				)
				(= cycles 1)
			)
			(1
				(HandsOff)
				(aBum setMotion: 0)
				(if (> (aBum distanceTo: ego) 45)
					(if (<= (ego x?) (aBum x?))
						(ego
							setMotion: PolyPath (- (aBum x?) 45) (aBum y?) self
						)
					else
						(ego
							setMotion: PolyPath (+ (aBum x?) 45) (aBum y?) self
						)
					)
				else
					(= cycles 1)
				)
			)
			(2
				(aBum
					setLoop: (+ 4 (mod (aBum loop?) 2))
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(if (== interactedWithBum TRUE)
					(switch register
						(verbTaste (Print 500 12))
						(verbDo (Print 500 13))
					)
				)
				(self cue:)
			)
			(4
				(if (== usedItemOnBum TRUE)
					(switch register
						(iRing (Print 500 14))
						(iRemoteControl (Print 500 15))
						(iRose (Print 500 16))
						(iCandy (Print 500 17))
						(iMagazine (Print 500 18))
						(iPills (Print 500 19))
						(iWhiskey (Print 500 20))
					)
				)
				(self cue:)
			)
			(5
				(if (OneOf register verbDo verbTalk verbZipper verbTaste
						iRemoteControl iRose iCandy iMagazine iPills iRing iWhiskey
					)
					(aBum
						setLoop: (+ 4 (mod (aBum loop?) 2))
						setCycle: BegLoop
					)
					(aBumHead
						init:
						cycleSpeed: (+ 3 howFast)
						setCycle: RandCycle 15 self
					)
				else
					(self cue:)
				)
			)
			(6
				(if (== interactedWithBum TRUE)
					(switch register
						(verbDo (Print 500 21))
						(verbTalk (Print 500 22))
						(verbZipper (Print 500 23))
					)
				)
				(self cue:)
			)
			(7
				(if (== usedItemOnBum TRUE)
					(switch register
						(iRemoteControl (ego put: iRemoteControl) (Print 500 24))
						(iRose (ego put: iRose) (Print 500 25))
						(iCandy (ego put: iCandy) (Print 500 26))
						(iMagazine
							(ego put: iMagazine)
							(Print 500 27)
						)
						(iPills
							(ego put: iPills)
							(Print 500 28)
						)
						(iRing (ego put: iRing) (Print 500 29))
						(iWhiskey (ego put: iWhiskey) (Print 500 30))
					)
				)
				(self cue:)
			)
			(8
				(= interactedWithBum FALSE)
				(= usedItemOnBum FALSE)
				(aBum setScript: sBumLeaves)
			)
		)
	)
)

(instance sBumLeaves of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== state 2) (!= bumDone TRUE) (aBum isBlocked: TRUE))
			(= bumDone TRUE)
			(Print 500 31)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(if (not (curRoom script?)) (HandsOn))
				(= seconds 1)
			)
			(1
				1
				(if
				(and (OneOf (aBum loop?) 4 5) (!= (aBum cel?) 0))
					(aBum setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(2
				2
				(aBum
					setLoop: 1
					setCycle: Walk
					setMotion: PolyPath -28 155 self
				)
			)
			(3
				3
				(aBum dispose:)
				(theMusic fade:)
				(self dispose:)
			)
		)
	)
)

(instance sHot_Bothered of Script
	(properties
		name "sHot&Bothered"
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(ego
					egoSpeed: (* 4 egoSpeed)
					view: 501
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(ego setLoop: 2 setCycle: ForwardCounter 10 self)
			)
			(3
				(ego view: 501 setLoop: 0 setCel: 5 setCycle: CycleTo 2 1 self)
			)
			(4
				(sfxHangUpHere play:)
				(ego setCycle: BegLoop self)
			)
			(5
				(Print 500 32 #at -1 20)
				(HandsOn)
				(Bclr fHeardSurveyFirstTime)
				(ego normal: TRUE)
				(NormalEgo 0)
				(self dispose:)
			)
		)
	)
)

(instance sFromTelephone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(ego moveHead: 0)
				(ego
					egoSpeed: (* 3 egoSpeed)
					view: 501
					setLoop: 0
					setCel: 5
					setCycle: CycleTo 2 -1 self
				)
			)
			(2
				(sfxHangUpHere play:)
				(ego setCycle: BegLoop self)
			)
			(3
				(HandsOn)
				(ego normal: TRUE)
				(NormalEgo 0)
				(self dispose:)
			)
		)
	)
)

(instance sToTelephone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(ego moveHead: 0)
				(ego
					egoSpeed: (* 3 egoSpeed)
					view: 501
					setLoop: 0
					setCel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(if (Btst fPhoneRinging) (sfxPhoneRinging stop:))
				(sfxHangUpHere play:)
				(ego setCycle: EndLoop self)
			)
			(3
				(if (Btst fPhoneRinging) (SolvePuzzle fAnswerPhone 5))
				(curRoom newRoom: rmKeypad)
			)
		)
	)
)

(instance sfxGlug of Sound
	(properties
		flags mNOPAUSE
		number 111
	)
)

(instance sfxHangUpHere of Sound
	(properties
		flags mNOPAUSE
		number 507
	)
)

(instance fEntryLights of Feature
	(properties
		x 48
		y 69
		nsTop 62
		nsLeft 31
		nsBottom 77
		nsRight 66
		description {the lights}
		sightAngle 40
		lookStr {The lights above the doorway illuminate the entrance to the Quiki-Mart store.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance darkAlley of Feature
	(properties
		x 31
		y 68
		nsTop -1
		nsBottom 138
		nsRight 63
		description {the dark alley}
		sightAngle 40
		lookStr {You're not sure, but you think you hear cries of "Help" coming from that dark alley over there.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk (Print 500 33))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance street of Feature
	(properties
		x 159
		y 175
		nsTop 161
		nsBottom 189
		nsRight 319
		description {the street}
		sightAngle 40
		lookStr {When you look hard enough, you think you can just barely make out the outline of a car speeding toward you.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fWindow of Feature
	(properties
		x 159
		y 79
		nsTop 50
		nsLeft 98
		nsBottom 108
		nsRight 220
		description {the window}
		sightAngle 40
		approachX 170
		approachY 120
		lookStr {This window looks perfectly rectangular -- to you!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 500 34))
			(verbTalk (Print 500 35))
			(verbUse
				(switch theItem
					(iRing (Print 500 36))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fSign of Feature
	(properties
		x 130
		y 146
		z 125
		nsTop -1
		nsLeft 22
		nsBottom 44
		nsRight 239
		description {the sign}
		sightAngle 40
		lookStr {This sign indicates you are currently in front of the lovely Lost Wages Quiki-Mart.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 500 37))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fTelephone of Feature
	(properties
		x 186
		y 150
		z 39
		nsTop 87
		nsLeft 178
		nsBottom 135
		nsRight 194
		description {the telephone}
		sightAngle 40
		approachX 172
		approachY 150
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(SolvePuzzle fLookAtPhone 1)
				(Print 500 38)
			)
			(verbDo
				(HandsOff)
				(curRoom setScript: sToTelephone)
			)
			(verbTalk (self doVerb: verbDo))
			(verbUse
				(switch theItem
					(iWallet (self doVerb: verbDo))
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

(instance fLight of Feature
	(properties
		x 171
		y 154
		z 107
		nsTop 40
		nsLeft 163
		nsBottom 55
		nsRight 180
		description {the light}
		sightAngle 40
		lookStr {The street light casts its yellow glow across the area, thoughtfully illuminating the many pieces of trash that line this thoroughfare.}
	)
)

(instance fArtGallery of Feature
	(properties
		x 286
		y 89
		nsTop 47
		nsLeft 254
		nsBottom 131
		nsRight 319
		description {the art gallery}
		sightAngle 40
		lookStr {The Lost Wages Art Emporium will satisfy your every need for modern art, as long as you're looking for something with dead fish!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 500 39))
			(verbTalk (Print 500 40))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance aBumHead of Prop
	(properties
		z 1000
		description {the bum}
		lookStr {The bum looks like a man that could (ab)use a drink.}
		view 502
		signal ignrAct
	)
	
	(method (doit)
		(super doit:)
		(if (OneOf (aBum loop?) 4 5)
			(aBumHead z: 22)
		else
			(aBumHead z: 1000)
		)
		(cond 
			((== (aBum loop?) 4) (aBumHead loop: 2 x: (+ (aBum x?) 10) y: (aBum y?)))
			((== (aBum loop?) 5) (aBumHead loop: 3 x: (- (aBum x?) 10) y: (aBum y?)))
		)
	)
	
	(method (doVerb theVerb theItem)
		(aBum doVerb: theVerb theItem)
	)
)

(instance aBum of Person
	(properties
		x -60
		y 154
		description {the bum}
		lookStr {The bum looks like a man that could (ab)use a drink.}
		yStep 1
		view 502
		xStep 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(= interactedWithBum TRUE)
				(aBum setScript: sBumInteraction 0 theVerb)
			)
			(verbTalk
				(= interactedWithBum TRUE)
				(aBum setScript: sBumInteraction 0 theVerb)
			)
			(verbZipper
				(= interactedWithBum TRUE)
				(aBum setScript: sBumInteraction 0 theVerb)
			)
			(verbTaste
				(= interactedWithBum TRUE)
				(aBum setScript: sBumInteraction 0 theVerb)
			)
			(verbUse
				(= usedItemOnBum TRUE)
				(switch theItem
					(iWine (aBum setScript: sGetKnife))
					(iWhiskey
						(aBum setScript: sBumInteraction 0 theItem)
					)
					(iWallet
						(Print 500 41)
						(= dollars (- dollars 1))
					)
					(iBreathSpray (Print 500 42))
					(iWatch
						(Print 500 43)
						(Print 500 44)
					)
					(iApple
						(Printf 500 45 theItem)
						(Print 500 46)
						(Print 500 47)
					)
					(iRing
						(aBum setScript: sBumInteraction 0 theItem)
					)
					(iRemoteControl
						(aBum setScript: sBumInteraction 0 theItem)
					)
					(iRose
						(aBum setScript: sBumInteraction 0 theItem)
					)
					(iLubber (Print 500 48))
					(iCandy
						(aBum setScript: sBumInteraction 0 theItem)
					)
					(iMagazine
						(aBum setScript: sBumInteraction 0 theItem)
					)
					(iPills
						(aBum setScript: sBumInteraction 0 theItem)
					)
					(else 
						(Printf 500 49 ((Inventory at: theItem) description?))
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sfxPhoneRinging of Sound
	(properties
		flags mNOPAUSE
		number 504
		loop -1
	)
)

(instance aBumPoly of Polygon
	(properties
		type PBarredAccess
	)
)
