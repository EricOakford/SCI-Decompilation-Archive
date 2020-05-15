;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmInsideTaxi) ;200
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use PrintD)
(use Feature)
(use LoadMany)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm200 0
)

(local
	destRoom
	lookedAtCabbie
	paidCabbie
	gaveDestination
	fare
	parkTimer
	local6
)
(instance rm200 of LLRoom
	(properties
		picture rmInsideTaxi
	)
	
	(method (init)
		(LoadMany VIEW 201 202 203 204)
		(User canControl: FALSE)
		(theIconBar disable: ICON_WALK)
		(ego init: hide: stopUpd:)
		(super init:)
		(globalSound fade:)
		(theMusic number: 206 vol: 127 loop: -1 flags: mNOPAUSE play:)
		(windshield
			cycleSpeed: (+ howFast 5)
			init:
			cel: (Random 0 4)
		)
		(driver cycleSpeed: howFast init:)
		(larry init: stopUpd:)
		(leftLight init: stopUpd:)
		(rightLight init: stopUpd:)
		(meter1 init:)
		(meter2 init:)
		(meter3 init:)
		(meter4 init:)
		(rightTurn cycleSpeed: (+ howFast 3) setCycle: Forward init:)
		(trunk init:)
		(license init:)
		(meter init:)
		(if (ego has: iWine)
			(HandsOff)
			(curRoom setScript: sWineScript)
		else
			(HandsOff)
			(curRoom setScript: sRoomScript)
		)
		(Bclr fFlag22)
		(= parkTimer 400)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 200 0))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance sRoomScript of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (ego mover?)
			(if (< ((curRoom script?) state?) 9)
				(Print 200 1)
				(ego setMotion: 0)
			else
				(if (not paidCabbie)
					(Bset fStiffedCabbie)
					(Print 200 2)
				else
					(SolvePuzzle fRodeInCab 1)
					(Print 200 3)
				)
				(curRoom newRoom: destRoom)
			)
		)
		(if
		(and parkTimer (== (-- parkTimer) 1))
			(cond 
				((< ((curRoom script?) state?) 3) (Print 200 4) (= parkTimer 400))
				((== ((curRoom script?) state?) 9) (Print 200 5) (= parkTimer 400))
				((== ((curRoom script?) state?) 10) (Print 200 6) (= parkTimer 400))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(driver setCycle: EndLoop self)
			)
			(1
				1
				(Print 200 7 #at 85 90 #time 6)
				(meter4 setCycle: Forward)
				(rightTurn setCycle: 0 setCel: 0 stopUpd:)
				(driver setCycle: BegLoop driver)
				(= cycles 15)
			)
			(2
				2
				(HandsOn)
				(leftTurn cycleSpeed: (+ howFast 3) setCycle: Forward init:)
				(rightLight setCel: 0 stopUpd:)
				(leftLight setCel: 0 stopUpd:)
				(windshield setCycle: Forward)
				(leftMirror
					cycleSpeed: (+ howFast 5)
					setCycle: Forward
					init:
				)
				(rightMirror
					setCycle: Forward
					cycleSpeed: (+ howFast 5)
					init:
				)
				(= cycles 60)
			)
			(3
				3
				(leftTurn setCycle: 0 setCel: 0 stopUpd:)
				(if (or gaveDestination local6) (= cycles 1))
			)
			(4
				4
				(User canControl: FALSE)
				(theIconBar disable: ICON_WALK)
				(if (windshield cycleSpeed?)
					(windshield cycleSpeed: (- (windshield cycleSpeed?) 1))
					(leftMirror cycleSpeed: (- (windshield cycleSpeed?) 1))
					(rightMirror cycleSpeed: (- (windshield cycleSpeed?) 1))
					(soundFx
						send: 9 255 (* 1000 (- 8 (windshield cycleSpeed?)))
					)
					(-- state)
					(= cycles 10)
				else
					(= cycles 1)
				)
			)
			(5 5 (if local6 (= cycles 1)))
			(6
				6
				(if (!= destRoom prevRoomNum)
					(= seconds (Random 12 18))
				else
					(= seconds 4)
				)
			)
			(7
				7
				(rightTurn setCycle: Forward)
				(leftLight setCel: 1 stopUpd:)
				(rightLight setCel: 1 stopUpd:)
				(= cycles 1)
			)
			(8
				8
				(if (< (windshield cycleSpeed?) 5)
					(windshield cycleSpeed: (+ (windshield cycleSpeed?) 1))
					(leftMirror cycleSpeed: (+ (windshield cycleSpeed?) 1))
					(rightMirror cycleSpeed: (+ (windshield cycleSpeed?) 1))
					(soundFx
						send: 9 255 (* 1000 (- 8 (windshield cycleSpeed?)))
					)
					(-- state)
					(= cycles 10)
				else
					(= cycles 1)
				)
			)
			(9
				9
				(meter4
					setCycle: 0
					setCel: (if (== (meter4 cel?) 9) 0 else (meter4 cel?))
				)
				(windshield setCycle: 0 stopUpd:)
				(leftMirror setCycle: 0 stopUpd:)
				(rightMirror setCycle: 0 stopUpd:)
				(= parkTimer 400)
				(User canControl: TRUE)
				(theIconBar enable: ICON_WALK)
				(Printf 200 8 fare)
			)
			(10
				10
				(= parkTimer 400)
				(Print 200 9)
			)
		)
	)
)

(instance sWineScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (driver setCycle: EndLoop self))
			(1
				(Print 200 10)
				(Print 200 11)
				(driver cel: 0)
				(rightTurn setCycle: Forward)
				(windshield setCycle: Forward)
				(leftMirror
					cycleSpeed: (+ howFast 5)
					setCycle: Forward
					init:
				)
				(rightMirror
					cycleSpeed: (+ howFast 5)
					setCycle: Forward
					init:
				)
				(meter4 setCycle: Forward)
				(= cycles 20)
			)
			(2
				(= temp0 (Random 0 3))
				(rightLight setCel: 0 stopUpd:)
				(leftLight setCel: 0 stopUpd:)
				(windshield
					x: (if (mod register 2)
						(if (Random 0 1) 138 else 178)
					else
						158
					)
					cycleSpeed: temp0
				)
				(leftMirror cycleSpeed: temp0)
				(rightMirror cycleSpeed: temp0)
				(if (== register 5) (Print 200 12))
				(if (< register 10)
					(-- state)
					(++ register)
					(= cycles (Random 5 10))
				else
					(windshield x: 138)
					(= cycles 10)
				)
			)
			(3
				(Print 200 13)
				(= seconds 3)
			)
			(4
				(ShowDeathIcon 203 6 1)
				(Format @str1 200 14)
				(EgoDead 200 15)
			)
		)
	)
)

(instance driver of Prop
	(properties
		x 101
		y 173
		z 100
		description {the driver}
		view 202
		priority 9
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem &tmp temp0)
		(switch theVerb
			(verbLook
				(if (not lookedAtCabbie)
					(= lookedAtCabbie TRUE)
					(Print 200 16)
				else
					(Print 200 17)
					(Print 200 18 #at -1 140)
				)
			)
			(verbDo
				(Print 200 19)
			)
			(verbZipper
				(Print 200 20)
			)
			(verbTaste
				(Print 200 21)
			)
			(verbTalk
				(cond 
					(
						(or
							(== ((curRoom script?) state?) 7)
							(== ((curRoom script?) state?) 8)
						)
						(Print 200 22)
					)
					((== ((curRoom script?) state?) 9) (Print 200 5) (= parkTimer 400))
					((== ((curRoom script?) state?) 10) (Print 200 6) (= parkTimer 400))
					(else
						(switch gaveDestination
							(FALSE
								(if (not (Btst fRodeInCab)) (Print 200 23) (Print 200 24))
								(= destRoom -1)
								(theGame setCursor: ARROW_CURSOR TRUE)
								(Animate (cast elements?) FALSE)
								(while (< destRoom 1)
									(= destRoom
										(PrintD
											{"Where to, Bub?" snorts the cabbie.}
											#new
											#button { Disco_} rmOutsideDisco
											#button { Casino_} rmOutsideCasino
											#button { Wedding Chapel_} rmOutsideChapel
											#new
											#button {Convenience Store} rmOutside7_11
											#button {Lefty's Bar} rmOutsideBar
										)
									)
								)
								(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
								(= fare (+ fare (Random 0 10) cabFareMin))
								(if (!= destRoom prevRoomNum)
									(Print 200 25)
								else
									(Print 200 26)
								)
								(if (< cabFareMin 40) (++ cabFareMin))
								(= local6 1)
							)
							(TRUE
								(Print 200 27)
								(theGame setCursor: ARROW_CURSOR TRUE)
								(Animate (cast elements?) FALSE)
								(= temp0
									(PrintD
										{"Yeah, OK, but it'll cos' ja extra!" the cabbie snarls.}
										#new
										#button { Disco_} rmOutsideDisco
										#button { Casino_} rmOutsideCasino
										#button { Wedding Chapel_} rmOutsideChapel
										#new
										#button {Convenience Store} rmOutside7_11
										#button {Lefty's Bar} rmOutsideBar
									)
								)
								(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
								(if (== temp0 -1) (= temp0 destRoom))
								(if (== temp0 destRoom)
									(Print 200 28)
								else
									(= destRoom temp0)
									(= fare (+ fare cabFareMin))
									(if (== ((curRoom script?) state?) 5)
										(sRoomScript start: 6)
										(curRoom setScript: sRoomScript)
									)
								)
							)
							(else 
								(Print 200 29)
								(Print 200 30)
							)
						)
						(if (== (sRoomScript state?) 3) (sRoomScript cue:))
						(++ gaveDestination)
					)
				)
			)
			(verbUse
				(switch theItem
					(iWallet
						(cond 
							((== ((curRoom script?) state?) 9)
								(if (< dollars fare)
									(Print 200 31)
									(Print 200 32)
									(Bset fStiffedCabbie)
									(curRoom newRoom: destRoom)
								else
									(= dollars (- dollars fare))
									(= paidCabbie TRUE)
									(sRoomScript start: 10)
									(curRoom setScript: sRoomScript)
								)
							)
							((== ((curRoom script?) state?) 10) (Print 200 33))
							(else (Print 200 34))
						)
					)
					(iBreathSpray
						(Print 200 35)
					)
					(iApple
						(Print 200 36)
					)
					(iWatch
						(Print 200 37)
						(Print 200 38)
					)
					(iRing
						(Print 200 39)
					)
					(iWhiskey
						(Print 200 40)
					)
					(iRemoteControl
						(Print 200 41)
					)
					(iLubber
						(Print 200 42)
					)
					(iCandy
						(Print 200 43)
						(ego put: iCandy 0)
						(Print 200 44 #at -1 140)
					)
					(iDiscoPass
						(Print 200 45)
					)
					(iGraffiti
						(Print 200 46)
					)
					(iPocketKnife
						(Print 200 47)
					)
					(iMagazine
						(Print 200 48)
					)
					(iHammer
						(Print 200 49)
						(Print 200 50 #at -1 140)
					)
					(iPills
						(Print 200 51)
						(ego put: iPills 0)
						(Print 200 52 #at -1 140)
					)
					(iRibbon
						(Print 200 53)
						(Print 200 54 #at -1 140)
					)
					(else 
						(Print 200 55)
						(Print 200 56)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self stopUpd:)
	)
)

(instance larry of Prop
	(properties
		x 204
		y 86
		z 14
		description {you}
		lookStr {It's not often you get to see the back of your own head, eh, Lar'?}
		view 201
		priority 10
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 200 57)
			)
			(verbTalk
				(Print 200 58)
			)
			(else
				(Print 200 59)
			)
		)
	)
)

(instance windshield of Prop
	(properties
		x 158
		y 85
		description {the street}
		lookStr {He sure drives fast, doesn't he?}
		view 204
	)
)

(instance leftMirror of Prop
	(properties
		x 52
		y 79
		description {the mirror}
		view 204
		loop 1
		detailLevel 1
	)
)

(instance rightMirror of Prop
	(properties
		x 266
		y 87
		description {the mirror}
		view 204
		loop 2
		cel 2
		detailLevel 1
	)
)

(instance leftLight of View
	(properties
		x 38
		y 114
		description {the light}
		view 203
		cel 1
		priority 14
		signal fixPriOn
	)
)

(instance rightLight of View
	(properties
		x 278
		y 108
		description {the light}
		view 203
		loop 1
		cel 1
		priority 14
		signal fixPriOn
	)
)

(instance leftTurn of Prop
	(properties
		x 45
		y 129
		description {the turn signal}
		view 203
		loop 2
		priority 14
		signal fixPriOn
		detailLevel 1
	)
)

(instance rightTurn of Prop
	(properties
		x 275
		y 128
		description {the turn signal}
		view 203
		loop 3
		cel 1
		priority 14
		signal fixPriOn
		detailLevel 1
	)
)

(instance meter1 of Prop
	(properties
		x 162
		y 62
		description {the meter}
		view 203
		loop 5
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(meter4 doVerb: theVerb theItem)
	)
)

(instance meter2 of Prop
	(properties
		x 169
		y 62
		description {the meter}
		view 203
		loop 5
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(meter4 doVerb: theVerb theItem)
	)
)

(instance meter3 of Prop
	(properties
		x 176
		y 62
		description {the meter}
		view 203
		loop 5
		priority 14
		signal fixPriOn
	)
	
	(method (doit)
		(super doit: &rest)
		(if (== (meter4 cel?) 9)
			(if (== (self cel?) 9)
				(self cel: 0)
				(if (== (meter2 cel?) 9)
					(meter2 cel: 0)
					(if (== (meter1 cel?) 9)
						(meter1 cel: 0)
					else
						(meter1 cel: (+ (meter1 cel?) 1))
					)
				else
					(meter2 cel: (+ (meter2 cel?) 1))
				)
			else
				(self cel: (+ (self cel?) 1))
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(meter4 doVerb: theVerb theItem)
	)
)

(instance meter4 of Prop
	(properties
		x 184
		y 63
		description {the meter}
		view 203
		loop 5
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 200 60)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance meter of Feature
	(properties
		x 170
		y 86
		z 27
		nsTop 53
		nsLeft 154
		nsBottom 66
		nsRight 186
		description {the taxicab's fare meter}
		sightAngle 40
		lookStr {It seems to bear no relationship whatsoever to the rate he charges!}
	)
)

(instance license of Feature
	(properties
		x 167
		y 109
		nsTop 95
		nsLeft 140
		nsBottom 123
		nsRight 195
		description {the cab's license plate}
		sightAngle 40
		lookStr {Isn't that unusual? Most taxicabs don't have customized license plates. You wonder if there's any special significance to the driver.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 200 61))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance trunk of Feature
	(properties
		x 163
		y 100
		nsTop 71
		nsLeft 96
		nsBottom 130
		nsRight 230
		description {the trunk lid}
		sightAngle 40
		lookStr {You wonder what the cabbie has hauled in there over the years!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 200 62)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
