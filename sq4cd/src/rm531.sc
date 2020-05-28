;;; Sierra Script 1.0 - (do not remove this comment)
(script# 531)
(include game.sh)
(use Main)
(use AudioScript)
(use SQRoom)
(use Sq4Dialog)
(use Sq4Feature)
(use Sync)
(use Sound)
(use Motion)
(use System)

(public
	rm531 0
)

(local
	outsideRoomNum
	handRaised
	currentLocation
	roomOverlay
	oldCur
	cursorX
	cursorY
	enterButton
	[inputButtons 12]
	[display 7]
	[buttonPosn 30] = [245 129 255 129 265 129 275 129 285 129 245 139 255 139 265 139 275 139 285 139 245 149 255 149 265 149 275 149 285 149]
	[timeCodes 37] = [
			5 4 3 2 1 0 ;Ortega
			20 20 20 20 20 20 ;Estros (replaced with second timecode try)
			2 20 20 20 20 20 ;Mall (replaced with random code)
			10 10 10 10 10 10 ;Xenon (replaced with random code)
			6 11 1 7 13 9 ;Ulence Flats
			4 14 6 9 1 11 ;I Love Lunacy
		]
	[local94 18] = [2 0 9 6 8 3 4 4 6 8 5 2 6]
	goingToOrtega
	goingToEstros
	goingToMall
	oldSoundNum
	oldVol
)

;Time-O-Matic destinations
(define timeORTEGA 0)
(define timeESTROS 6)
(define timeMALL 12)
(define timeXENON 18)
(define timeULENCE 24)
(define timeLUNACY 30)
		
(procedure (DisplayTimeCode param1)
	(return
		(if
			(and
				(==
					([display 0] cel?)
					[timeCodes param1]
				)
				(==
					([display 1] cel?)
					[timeCodes (++ param1)]
				)
				(==
					([display 2] cel?)
					[timeCodes (++ param1)]
				)
				(==
					([display 3] cel?)
					[timeCodes (++ param1)]
				)
				(==
					([display 4] cel?)
					[timeCodes (++ param1)]
				)
			)
			(==
				([display 5] cel?)
				[timeCodes (++ param1)]
			)
		else
			FALSE
		)
	)
)

(procedure (localproc_12fb param1)
	(return
		(if
			(and
				(== ([display 0] cel?) [local94 (+ 0 param1)])
				(== ([display 1] cel?) [local94 (+ 1 param1)])
				(== ([display 2] cel?) [local94 (+ 2 param1)])
				(== ([display 3] cel?) [local94 (+ 3 param1)])
				(== ([display 4] cel?) [local94 (+ 4 param1)])
			)
			(== ([display 5] cel?) [local94 (+ 5 param1)])
		else
			FALSE
		)
	)
)

(procedure (ShowOverlay roomNum &tmp temp0)
	(curRoom
		overlay:
			(switch roomNum
				(305
					(windshield noun: 0 lookStr: 31)
					(ShowStatus 10)
					(= currentLocation timeESTROS)
					533
				)
				(376
					(windshield noun: 0 lookStr: 32)
					(ShowStatus 10)
					(= currentLocation timeMALL)
					534
				)
				(530
					(windshield noun: 0 lookStr: 33)
					(ShowStatus 12)
					(= currentLocation timeXENON)
					532
				)
				(613
					(windshield noun: 0 lookStr: 34)
					(ShowStatus 1)
					(= currentLocation timeULENCE)
					631
				)
				(650
					(windshield noun: 0 lookStr: 35)
					(ShowStatus 3)
					(= currentLocation timeORTEGA)
					651
				)
				(271
					(windshield noun: 0)
					(ShowStatus 3)
					(= currentLocation timeORTEGA)
					529
				)
				(else 
					(windshield noun: 0 lookStr: 36)
					(ShowStatus 12)
					(= prevRoomNum 530)
					(= currentLocation timeXENON)
					532
				)
			)
	)
)

(instance rm531 of SQRoom
	(properties
		picture 531
	)
	
	(method (init &tmp temp0 i temp2)
		(= oldCur normalCursor)
		(= normalCursor 852)
		(Load PICTURE 529)
		(if (not (Btst fIsVGA)) (Load PICTURE 602))
		(ego view: 0)
		(super init:)
		(windshield init:)
		(ShowOverlay (= roomOverlay prevRoomNum))
		(= i 0)
		(while (<= i 5)
			(= [timeCodes (+ timeXENON i)]
				[timeCodeXenon i]
			)
			(++ i)
		)
		(if (Btst fEnterArcadeTimepod)
			(= i 0)
			(while (<= i 5)
				(= [timeCodes (+ timeMALL i)]
					[timeCodeMall i]
				)
				(++ i)
			)
		)
		(if (and (OneOf prevRoomNum 305 376 530) (Btst fTimeTravel))
			(Bclr fTimeTravel)
		)
		(if (>= timeWarpEntries 1)
			(= i 0)
			(while (<= i 5)
				(= [timeCodes (+ timeESTROS i)]
					[timeCodeEstros i]
				)
				(++ i)
			)
		)
		(= i 0)
		(while (<= i 14)
			((= [inputButtons i] (butn new:))
				posn: [buttonPosn (= temp2 (* i 2))] [buttonPosn (++ temp2)]
				cel: i
				init:
				setPri: 0
				stopUpd:
			)
			(++ i)
		)
		(= i 0)
		(while (<= i 5)
			((= [display i] (theDisplay new:))
				posn: (- 289 (* i 7)) 114
				cel: [timeCodes (+ currentLocation i)]
				init:
			)
			(++ i)
		)
		((= enterButton (butn new:))
			noun: 0
			lookStr: 1
			posn: 295 148
			cel: 15
			init:
			setPri: 0
			stopUpd:
		)
		(hand init:)
		(rogerHead init:)
		(keyDisplay init:)
		(mainScreen init:)
		(smallCompartment init:)
		(largeCompartment init:)
		(headRest init:)
		(cables init:)
		(entryPad init:)
		(soundFX init:)
		(exitButn init:)
		(restOfPod init:)
		(HandsOn)
		(user canControl: FALSE)
		(theIconBar disable: ICON_WALK ICON_SMELL ICON_TASTE)
		(theGame setCursor: 852 TRUE)
		(theIconBar curIcon: (theIconBar at: ICON_DO))
	)
	
	(method (newRoom)
		(if (sounds contains: soundFX) (soundFX dispose:))
		(= normalCursor oldCur)
		(super newRoom: &rest)
	)
)

(instance raiseHandS of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: INVIS_CURSOR TRUE cursorX cursorY)
				(hand setMotion: MoveTo cursorX cursorY self)
			)
			(1 (= handRaised 1) (= cycles 2))
			(2
				(theGame setCursor: INVIS_CURSOR TRUE cursorX cursorY)
				(self dispose:)
			)
		)
	)
)

(instance hand of Sq4Actor
	(properties
		x 300
		y 200
		yStep 15
		view 531
		priority 14
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		illegalBits $0000
		xStep 15
	)
	
	(method (doit &tmp userCurEvent temp1)
		(cond 
			(
				(and
					(not script)
					(== ((theIconBar curIcon?) message?) keyDown)
				)
				(if
					(not
						(& ((= userCurEvent (user curEvent?)) type?) (| mouseUp mouseDown))
					)
					(userCurEvent localize:)
					(= cursorX (userCurEvent x?))
					(= cursorY (userCurEvent y?))
					(= temp1
						(InRect 230 118 308 170 cursorX cursorY)
					)
					(cond 
						((and (not handRaised) temp1) (self setScript: raiseHandS))
						(temp1 (self posn: cursorX cursorY))
						(handRaised
							(if (> cursorY 170)
								(self setScript: lowerHandS 0 2)
							else
								(if (< cursorY 118) (= cursorY 118))
								(= cursorX
									(cond 
										((> cursorX 308) 308)
										((< cursorX 230) 230)
										(else cursorX)
									)
								)
								(theGame setCursor: INVIS_CURSOR TRUE cursorX cursorY)
								(self posn: cursorX cursorY)
							)
						)
					)
				)
			)
			((and (not script) handRaised) (self setScript: lowerHandS 0 1))
		)
		(super doit:)
	)
)

(instance lowerHandS of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hand setMotion: MoveTo 270 204 self)
			)
			(1
				(= handRaised FALSE)
				(if register
					(switch register
						(1
							(theGame
								setCursor: theCursor TRUE cursorX cursorY
							)
						)
						(2
							(theGame setCursor: normalCursor TRUE cursorX 175)
						)
					)
					(self dispose:)
				else
					(theGame setCursor: normalCursor TRUE cursorX 175)
					(caller cue:)
				)
			)
		)
	)
)

(instance pushAndBackS of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(hand posn: (+ (hand x?) 1) (+ (hand y?) 1))
				(= cycles 6)
			)
			(1
				(hand posn: (- (hand x?) 1) (- (hand y?) 1))
				(theGame setCursor: INVIS_CURSOR TRUE (hand x?) (hand y?))
				(self dispose:)
			)
		)
	)
)

(instance displayMeS of Script
	(properties)
	
	(method (changeState newState &tmp i temp1)
		(switch (= state newState)
			(0
				(user canInput: FALSE)
				(soundFX number: 102 loop: 0 play:)
				(client setPri: 10)
				(= cycles 1)
			)
			(1
				(if (!= ([display 5] loop?) 4)
					(= i 5)
					(while (>= i 0)
						([display i] loop: 4)
						(-- i)
					)
				else
					(= i 4)
					(while (>= i 0)
						(if (!= ([display i] loop?) 4)
							([display (+ i 1)] loop: 2)
							([display (+ i 1)]
								cel: ([display i] cel?)
							)
						)
						(-- i)
					)
				)
				([display 0] loop: 2 cel: (client cel?))
				(= cycles 7)
			)
			(2
				(client setPri: 0)
				(user canInput: TRUE)
				(self dispose:)
			)
		)
	)
)

(instance dudScript of Script
	(properties)
	
	(method (changeState newState &tmp i)
		(switch (= state newState)
			(0
				(lowerHandS caller: 0)
				(hand setScript: 0)
				(if outsideRoomNum
					(narrator say: 5 self)
				else
					(narrator say: 6 self)
				)
			)
			(1
				(= i 0)
				(while (<= i 5)
					([display i] loop: 4)
					(++ i)
				)
				(= cycles 1)
			)
			(2
				(if (!= (music prevSignal?) -1) (music fade: 0 0 0 1))
				(HandsOff)
				(= cycles 1)
			)
			(3
				(if (!= (music prevSignal?) -1) (-- state))
				(= cycles 1)
			)
			(4 (= seconds 2))
			(5
				(theGame setCursor: 852 TRUE)
				(theIconBar curIcon: (theIconBar at: ICON_DO))
				(user canInput: TRUE)
				(theIconBar enable: 2 3 1)
				(= cycles 2)
			)
			(6
				(if (OneOf oldSoundNum 15 14 535 19)
					(globalSound
						number: oldSoundNum
						loop: -1
						vol: oldVol
						flags: 1
						prevSignal: 0
						play:
					)
				)
				(= cycles 1)
			)
			(7 (self dispose:))
		)
	)
)

(instance timeToTimeWarpS of AudioScript
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if register (Palette PALCycle 190 220 -2))
	)
	
	(method (changeState newState &tmp i temp1)
		(switch (= state newState)
			(0
				(= register 0)
				(= oldSoundNum (globalSound number?))
				(= oldVol (globalSound vol?))
				(HandsOff)
				(soundFX number: 102 loop: 0 playBed:)
				(enterButton setPri: 10)
				(if (DisplayTimeCode timeLUNACY)
					(heart init: setScript: ILoveLunacyScript)
					(self dispose:)
				else
					(= cycles 2)
				)
			)
			(1
				(cond 
					(
						(and
							(localproc_12fb timeORTEGA)
							(not goingToOrtega)
							(not goingToEstros)
							(not goingToMall)
						)
						(= goingToOrtega TRUE)
					)
					(
						(and
							(localproc_12fb timeESTROS)
							goingToOrtega
							(not goingToEstros)
							(not goingToMall)
						)
						(= goingToEstros TRUE)
					)
					(
						(and
							(localproc_12fb timeMALL)
							goingToOrtega
							goingToEstros
							(not goingToMall)
						)
						(= goingToMall TRUE)
					)
					(else (= goingToOrtega FALSE) (= goingToEstros FALSE) (= goingToMall FALSE))
				)
				(= outsideRoomNum
					(cond 
						((DisplayTimeCode timeORTEGA) 650)
						((DisplayTimeCode timeMALL) 376)
						((DisplayTimeCode timeXENON) 530)
						((DisplayTimeCode timeULENCE) (SolvePuzzle fGoToUlence 5) 613)
						((and goingToOrtega goingToEstros goingToMall) 271)
						((and (== timeWarpEntries 1) (DisplayTimeCode timeESTROS))
							(= i 0)
							(while (<= i 5)
								([display i] loop: 4)
								(++ i)
							)
							0
						)
						(
						(and (== timeWarpEntries 1) (!= ([display 5] loop?) 4))
							(= timeWarpEntries 2)
							(= i 0)
							(while (<= i 5)
								(= [timeCodeEstros i]
									(= [timeCodes (+ 6 i)]
										([display i] cel?)
									)
								)
								(++ i)
							)
							(Bset fEnterArcadeTimepod)
							(if (> timeCodeEstros 10)
								(= timeCodeMall 2)
							else
								(= timeCodeMall 13)
							)
							(= i 1)
							(while (<= i 5)
								(= [timeCodeMall i] (Random 0 14))
								(++ i)
							)
							305
						)
						(
						(and (== timeWarpEntries 0) (!= ([display 5] loop?) 4))
							(= timeWarpEntries 1)
							(= i 0)
							(while (<= i 5)
								(= [timeCodeEstros i]
									(= [timeCodes (+ 6 i)]
										([display i] cel?)
									)
								)
								(++ i)
							)
							(= i 0)
							(while (<= i 5)
								([display i] loop: 4)
								(++ i)
							)
							0
						)
						((DisplayTimeCode timeESTROS) 305)
						(else
							(= i 0)
							(while (<= i 5)
								([display i] loop: 4)
								(++ i)
							)
							0
						)
					)
				)
				(hand setScript: lowerHandS self 0)
			)
			(2
				(= seconds 1)
				(globalSound stop:)
			)
			(3
				(HandsOff)
				(enterButton setPri: 0)
				(narrator modeless: TRUE say: 2 self)
			)
			(4
				(if (& msgType CD_MSG)
					(= waitForCue 256)
					(dummy init: 531 99 0 3 1)
					(narrator modeless: TRUE say: 3 self)
				else
					(narrator modeless: TRUE say: 3 self)
				)
			)
			(5
				(if
					(and
						outsideRoomNum
						(!= outsideRoomNum roomOverlay)
					)
					(music
						number: 17
						loop: 0
						vol: 127
						flags: 1
						prevSignal: 0
						play:
					)
				else
					(music
						number: 16
						loop: 0
						vol: 127
						flags: 1
						prevSignal: 0
						play:
					)
				)
				(= cycles 1)
			)
			(6
				(if (!= (narrator ticks?) -1) (-- state))
				(= cycles 1)
			)
			(7
				(narrator modeless: FALSE say: 4 self)
			)
			(8
				(= seconds 0)
				(if modelessDialog (modelessDialog dispose:))
				(if
					(and
						outsideRoomNum
						(!= outsideRoomNum roomOverlay)
					)
					(= i 0)
					(while (<= i 5)
						([display i] hide:)
						(++ i)
					)
					(= cycles 2)
					(if (Btst fPoliceInSkateORama)	;Mall becomes safe to return to
						(Bclr fPoliceInSkateORama)
						(Bset fSocksClosed)	;Socks closes, but all other stores reopen
						(Bclr fBigTallClosed)
						(Bclr fMonolithBurgerClosed)
						(Bclr fSoftwareExcessClosed)
						(Bclr fRadioShockClosed)
						(Bclr fPoliceAtCeiling)
						(Bclr fPoliceAtArcade)
						(Bclr fPoliceLookForEgo)
						(Bclr fPoliceAtMallEntrance)
						(Bclr fPoliceLookForEgo) ;EO: duplicate
						(Bset fEscapeMall)
					)
				else
					(curRoom setScript: dudScript)
					(self dispose:)
				)
			)
			(9
				(curRoom overlay: 529)
				(= cycles 2)
			)
			(10
				(= temp1 529)
				(= register 1)
				(music prevSignal: 0)
				(= cycles 1)
			)
			(11
				(if (!= (music prevSignal?) -1) (-- state))
				(= cycles 1)
			)
			(12
				(= register 0)
				(Bclr fPoliceArrive)
				(lowerHandS caller: 0)
				(hand setScript: 0)
				(switch outsideRoomNum
					(650
						(globalSound
							number: 15
							loop: -1
							vol: 90
							flags: 1
							playBed:
						)
					)
					(613
						(globalSound
							number: 14
							vol: 127
							loop: 1
							flags: 1
							playBed:
						)
					)
					(530
						(globalSound
							number: 535
							vol: 80
							loop: -1
							flags: 1
							playBed:
						)
					)
					(271
						(globalSound stop:)
						(music stop:)
					)
					(376
						(globalSound
							number: 19
							loop: -1
							vol: 80
							flags: 1
							playBed:
						)
					)
				)
				(if (and goingToOrtega goingToEstros goingToMall)
					(curRoom newRoom: outsideRoomNum)
				)
				(if (OneOf outsideRoomNum 305 376 530)
					(Bset fTimeTravel)
					(Bset fFirstTimeTravel)
					(curRoom newRoom: outsideRoomNum)
				else
					(= roomOverlay outsideRoomNum)
					(ShowOverlay outsideRoomNum)
					(HandsOn)
					(user canControl: FALSE)
					(theIconBar disable: ICON_WALK ICON_SMELL ICON_TASTE)
					(= i 0)
					(while (<= i 5)
						([display i] show:)
						(++ i)
					)
					(user canInput: TRUE)
					(self dispose:)
				)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hand
					x: 55
					y: 189
					setLoop: 1
					setMotion: MoveTo 37 151 self
				)
			)
			(1 (= cycles 3))
			(2
				(soundFX number: 812 loop: 0 play:)
				(hand setMotion: MoveTo 41 151 self)
			)
			(3 (= cycles 3))
			(4
				(hand setMotion: MoveTo 55 189 self)
			)
			(5
				(curRoom newRoom: roomOverlay)
			)
		)
	)
)

(instance butn of Sq4Prop
	(properties
		x 247
		y 128
		view 531
		loop 3
		priority 10
		signal ignrAct
		lookStr 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK (narrator say: 8))
			(V_DO
				(if
					(and
						(not script)
						(not (hand script?))
						(not (curRoom script?))
					)
					(if (== cel 15)
						(curRoom setScript: timeToTimeWarpS)
					else
						(hand setScript: pushAndBackS)
						(self setScript: displayMeS)
					)
				)
			)
			(
				(OneOf theVerb
					V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR
					V_GUM V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR
					V_MATCHES V_DISKETTE V_LAPTOP
				)
				0
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(super cue: &rest)
	)
)

(instance theDisplay of Sq4View
	(properties
		view 531
		loop 2
		priority 10
		lookStr 9
	)
)

(instance windshield of Sq4Feature
	(properties)
	
	(method (onMe event)
		(return (== (OnControl cGREEN (event x?) (event y?)) 2))
	)
)

(instance rogerHead of Sq4Feature
	(properties
		lookStr 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK (narrator say: 11))
			(V_DO (narrator say: 12))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(return (== (OnControl cGREEN (event x?) (event y?)) 4))
	)
)

(instance mainScreen of Sq4Feature
	(properties
		lookStr 13
	)
	
	(method (onMe event)
		(return (== (OnControl cGREEN (event x?) (event y?)) 8))
	)
)

(instance keyDisplay of Sq4Feature
	(properties
		lookStr 14
	)
	
	(method (onMe event)
		(return (== (OnControl cGREEN (event x?) (event y?)) 16))
	)
)

(instance smallCompartment of Sq4Feature
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 15))
			(V_DO (narrator say: 16))
			(
				(OneOf theVerb
					V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR
					V_GUM V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR
					V_MATCHES V_DISKETTE V_LAPTOP
				)
				0
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return (== (OnControl cGREEN (event x?) (event y?)) 32))
	)
)

(instance largeCompartment of Sq4Feature
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 17))
			(V_DO (narrator say: 18))
			(
				(OneOf theVerb
					V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR
					V_GUM V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR
					V_MATCHES V_DISKETTE V_LAPTOP
				)
				0
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(return (== (OnControl cGREEN (event x?) (event y?)) 64))
	)
)

(instance headRest of Sq4Feature
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 19))
			(V_DO (narrator say: 20))
			(
				(OneOf theVerb
					V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR
					V_GUM V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR
					V_MATCHES V_DISKETTE V_LAPTOP
				)
				0
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(return (== (OnControl cGREEN (event x?) (event y?)) 128))
	)
)

(instance cables of Sq4Feature
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 21))
			(V_DO (narrator say: 22))
			(
				(OneOf theVerb
					V_BUCKAZOID V_ROPE V_BOMB V_RABBIT V_BATTERY V_JAR
					V_GUM V_TANK V_HINTBOOK V_PEN V_ATMCARD V_PLUG V_CIGAR
					V_MATCHES V_DISKETTE V_LAPTOP
				)
				0
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(return (== (OnControl 4 (event x?) (event y?)) 256))
	)
)

(instance entryPad of Sq4Feature
	(properties
		lookStr 24
	)
	
	(method (onMe event)
		(return (== (OnControl 4 (event x?) (event y?)) 512))
	)
)

(instance exitButn of Sq4Feature
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 25))
			(V_DO
				(if (or (hand script?) (curRoom script?))
				else
					(HandsOff)
					(= handRaised 1)
					(hand setScript: exitScript)
				)
			)
			(6 (narrator say: 26))
			(7 (narrator say: 27))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(return (== (OnControl 4 (event x?) (event y?)) 1024))
	)
)

(instance restOfPod of Sq4Feature
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 28))
			(V_DO (narrator say: 29))
			(V_SMELL (narrator say: 30))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe)
		(return TRUE)
	)
)

(instance soundFX of Sound
	(properties
		number 102
		priority 5
	)
)

(instance frog of Sq4Feature
	(properties
		nsTop 183
		nsLeft 27
		nsBottom 189
		nsRight 62
		lookStr 37
	)
	
	(method (doVerb &tmp temp0)
		(= currentLocation
			(SQ4Print {Where To?}
				#mode teJustCenter
				#title {Time-O-Matic}
				#new
				#button {Estros} 6
				#new
				#button {Mall} 12
				#new
				#button {Xenon} 18
				#new
				#button {Ulence} 24
				#new
				#button {Ortega} 0
				#new
			)
		)
		(cond 
			((and (< timeWarpEntries 2) (== currentLocation timeESTROS)) (SQ4Print 531 0))
			((and (< timeWarpEntries 2) (== currentLocation 12)) (SQ4Print 531 1))
			(else
				(= temp0 0)
				(while (<= temp0 5)
					([display temp0]
						loop: 2
						cel: [timeCodes (+ currentLocation temp0)]
					)
					(++ temp0)
				)
				(hand posn: 180 180)
				(curRoom setScript: timeToTimeWarpS)
			)
		)
	)
)

(instance heart of Sq4Prop
	(properties
		x 194
		y 131
		view 888
		cel 1
		priority 10
		signal (| ignrAct fixPriOn)
	)
)

(instance ILoveLunacy of Sq4View
	(properties
		x 194
		y 131
		view 888
		loop 1
		priority 11
		signal (| ignrAct fixPriOn)
	)
)

(instance ILoveLunacyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(heart cycleSpeed: 30 setCycle: Forward)
				(= cycles 2)
			)
			(1
				(enterButton setPri: 0)
				(= seconds 3)
			)
			(2
				(ILoveLunacy init:)
				(= seconds 5)
			)
			(3
				(ILoveLunacy dispose:)
				(= cycles 3)
			)
			(4 (heart hide:) (= cycles 2))
			(5
				(narrator say: 39)
				(user canInput: 1)
				(theIconBar enable: 2 3 1)
				(heart dispose:)
			)
		)
	)
)

(instance musicDummy of Script
	(properties)
	
	(method (cue)
		(if (OneOf (globalSound number?) 19 535 15)
			(globalSound vol: 80 loop: -1 flags: 1 play:)
		)
	)
)

(instance dummy of ScriptSync
	(properties)
)
