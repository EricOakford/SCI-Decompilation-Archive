;;; Sierra Script 1.0 - (do not remove this comment)
(script# 800)
(include game.sh) (include "800.shm")
(use Main)
(use String)
(use Array)
(use Inset)
(use Polygon)
(use Feature)
(use Cursor)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	foodReplicatorInset 0
)

(local
	button0
	button1
	button2
	button3
	button4
	button5
	button6
	button7
	button8
	button9
	newButtonView_6
	newButtonView_5
	starButton
	backspaceButton
	newButtonView_4
	bjornChowCode
	newStr
	newIntArray
	numIntegers
)
(instance foodReplicatorInset of Inset
	(properties
		picture 800
		y 0
		disposeNotOnMe TRUE
		modNum 800
		noun N_FOOD_REPLICATOR_INSET
		plane 1
	)
	
	(method (init &tmp temp0)
		(= numIntegers (= plane 0))
		(super init: &rest)
		(if (== curRoomNum 390)
			(self noun: N_ROGER_REPLICATOR)
			(rogerReplicator init:)
			(bullethole init:)
			(= temp0 (RandTime 1 9))
			((= button0 (aBug new:)) init:)
			((= button1 (aBug new:)) init:)
			((= button2 (aBug new:)) init:)
			(if (> temp0 3) ((= button3 (aBug new:)) init:))
			(if (> temp0 4) ((= button4 (aBug new:)) init:))
			(if (> temp0 5) ((= button5 (aBug new:)) init:))
			(if (> temp0 6) ((= button6 (aBug new:)) init:))
			(if (> temp0 7) ((= button7 (aBug new:)) init:))
			(if (> temp0 8) ((= button8 (aBug new:)) init:))
		else
			(self noun: N_FOOD_REPLICATOR_INSET)
			((= button0 (buttonView new:))
				setLoop: 0
				setCel: 0
				init:
				posn: 151 123
				noun: N_BUTTON0
			)
			((= button1 (buttonView new:))
				setLoop: 1
				setCel: 0
				init:
				posn: 140 111
				noun: N_BUTTON1
			)
			((= button2 (buttonView new:))
				setLoop: 2
				setCel: 0
				init:
				posn: 151 111
				noun: N_BUTTON2
			)
			((= button3 (buttonView new:))
				setLoop: 3
				setCel: 0
				init:
				posn: 163 111
				noun: N_BUTTON3
			)
			((= button4 (buttonView new:))
				setLoop: 4
				setCel: 0
				init:
				posn: 140 115
				noun: N_BUTTON4
			)
			((= button5 (buttonView new:))
				setLoop: 5
				setCel: 0
				init:
				posn: 151 115
				noun: N_BUTTON5
			)
			((= button6 (buttonView new:))
				setLoop: 6
				setCel: 0
				init:
				posn: 163 115
				noun: N_BUTTON6
			)
			((= button7 (buttonView new:))
				setLoop: 7
				setCel: 0
				init:
				posn: 139 119
				noun: N_BUTTON7
			)
			((= button8 (buttonView new:))
				setLoop: 8
				setCel: 0
				init:
				posn: 151 119
				noun: N_BUTTON8
			)
			((= button9 (buttonView new:))
				setLoop: 9
				setCel: 0
				init:
				posn: 163 119
				noun: N_BUTTON9
			)
			((= starButton (buttonView new:))
				setLoop: 10
				setCel: 0
				init:
				posn: 139 123
				noun: N_STAR_BUTTON
			)
			((= backspaceButton (buttonView new:))
				setLoop: 13
				setCel: 0
				init:
				posn: 174 115
				noun: N_BACKSPACE_BUTTON
			)
			((= newButtonView_4 (buttonView new:))
				setLoop: 12
				setCel: 0
				init:
				posn: 174 111
				noun: 27
			)
			((= newButtonView_5 (buttonView new:))
				setLoop: 11
				setCel: 0
				init:
				posn: 163 123
				noun: 14
			)
			((= newButtonView_6 (buttonView new:))
				setLoop: 14
				setCel: 0
				init:
				posn: 175 119
				noun: 12
			)
			(= newStr (String new:))
			(= bjornChowCode (String with: {7469410}))
			(= newIntArray (IntArray new:))
			(door init:)
			(titleScreen init:)
			(blackPanel init:)
		)
		(if (and (== curRoomNum 430) (Btst fChowReady))
			(theBjornChow init: posn: 160 93)
		)
		(light init:)
		(leftVents init:)
		(rightVents init:)
		(access1 init:)
		(access2 init:)
		(access3 init:)
		(access4 init:)
		(screen init:)
		(pipeL init:)
		(pipeR init:)
		(theIconBar setupExit: TRUE disable: V_DO 6)
		(if (and (not (Btst fVisitedReplicator)) (== curRoomNum 430))
			(sound1 number: 435 setLoop: 0 play:)
		else
			(sound1 setVol: 90 setLoop: -1)
		)
	)
	
	(method (doit &tmp userCurEvent)
		(if
			(and
				disposeNotOnMe
				(user canInput:)
				(not ((= userCurEvent (user curEvent?)) type?))
				(not ((theIconBar plane?) onMe: (user curEvent?)))
			)
			(userCurEvent localize: (cast plane?))
			(cond 
				(
					(and
						(not (self onMe: userCurEvent))
						(< (userCurEvent y?) 136)
					)
					(if (!= theCursor theExitCursor)
						(theGame setCursor: theExitCursor TRUE)
					)
				)
				(
					(and
						(!= (theIconBar curIcon?) (theIconBar useIconItem?))
						(!= theCursor ((theIconBar curIcon?) cursorView?))
					)
					(theGame
						setCursor: ((theIconBar curIcon?) cursorView?) 1
					)
				)
				(
					(and
						(== (theIconBar curIcon?) (theIconBar useIconItem?))
						theCursor
						(!=
							(theCursor view?)
							((theIconBar curIcon?) cursorView?)
						)
					)
					(inCursor
						view: ((theIconBar useIconItem?) cursorView?)
						loop: ((theIconBar useIconItem?) cursorLoop?)
						cel: ((theIconBar useIconItem?) cursorCel?)
					)
					(theGame setCursor: inCursor TRUE)
				)
			)
		)
		(if script (script doit:))
	)
	
	(method (dispose)
		(theIconBar setupExit: FALSE enable: V_DO 6)
		(cast eachElementDo: #dispose)
		(if (!= curRoomNum 390)
			(bjornChowCode dispose:)
			(newStr dispose:)
			(newIntArray dispose:)
		else
			(rogerReplicator dispose:)
		)
		(cond 
			(
				(and
					(!= (theIconBar curIcon?) (theIconBar useIconItem?))
					(!= theCursor ((theIconBar curIcon?) cursorView?))
				)
				(theGame
					setCursor: ((theIconBar curIcon?) cursorView?) 1
				)
			)
			(
				(and
					(== (theIconBar curIcon?) (theIconBar useIconItem?))
					theCursor
					(!=
						(theCursor view?)
						((theIconBar curIcon?) cursorView?)
					)
				)
				(inCursor
					view: ((theIconBar useIconItem?) cursorView?)
					loop: ((theIconBar useIconItem?) cursorLoop?)
					cel: ((theIconBar useIconItem?) cursorCel?)
				)
				(theGame setCursor: inCursor TRUE)
			)
		)
		(if (and (not (Btst fVisitedReplicator)) (== curRoomNum 430))
			(sound1 number: 102 flags: 1 loop: -1 play:)
			(Bset fVisitedReplicator)
		else
			(sound1 setVol: 127)
		)
		(super dispose:)
	)
	
	(method (onMe param1 param2 &tmp temp0 temp1)
		(if (== argc 1)
			(param1 localize: plane)
			(= temp0 (param1 x?))
			(= temp1 (param1 y?))
		else
			(= temp0 param1)
			(= temp1 param2)
		)
		(return
			(if
			(and (<= 84 temp0) (<= temp0 229) (<= 0 temp1))
				(<= temp1 138)
			else
				0
			)
		)
	)
)

(instance sFoodArrives of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar enable: V_DO 6)
				(theGame handsOff:)
				(bg init:)
				(sound2 number: 117 loop: 1 play:)
				(door setCycle: EndLoop self)
			)
			(1
				(sound2 number: 118 loop: 1 play:)
				(sparkle
					view: 804
					loop: 0
					cel: 0
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(theBjornChow init: setPri: 68 posn: 160 84)
				(Bset fChowReady)
				(theBjornChow setPri: 101 setMotion: MoveTo 160 93 self)
			)
			(3
				(sparkle view: 8040 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(sound2 stop:)
				(sparkle dispose:)
				(sound2 number: 117 loop: 1 play:)
				(door setCycle: BegLoop self)
			)
			(5
				(= numIntegers numIntegers)
				(while (> numIntegers 0)
					(newStr at: (- numIntegers 1) 0)
					((newIntArray at: (- numIntegers 1)) dispose:)
					(-- numIntegers)
				)
				(titleScreen show:)
				(theGame handsOn:)
				(theIconBar disable: V_DO 6)
			)
		)
	)
)

(instance aBug of Actor
	(properties
		noun N_BUG
		modNum 800
		view 802
		loop 3
		yStep 1
		signal (| ignrAct fixedLoop canUpdate) ;$4801
		xStep 1
	)
	
	(method (init)
		(self x: (RandTime 100 220) y: (RandTime 96 110))
		(super init:)
		(self
			moveSpeed: (RandTime 3 6)
			cycleSpeed: 1
			setLoop: (if (< x 160) 1 else 2)
			setCycle: Forward
			setMotion:
				MoveTo
				(if (< x 160)
					(- x (RandTime 5 20))
				else
					(+ x (RandTime 5 20))
				)
				150
		)
	)
	
	(method (doit)
		(cond 
			((== loop 6))
			((and (> y 90) (< y 100) (not scratch)) (self setLoop: 5 scratch: 1))
			(
				(and
					(< 100 y)
					(< y 105)
					(or (not (== loop 1)) (not (== loop 2)))
				)
				(self setLoop: (if (< x 160) 1 else 2))
			)
			(
				(and
					(< 105 y)
					(< y 128)
					(or (not (== loop 3)) (not (== loop 4)))
				)
				(self
					setLoop: (if (< x 160) 3 else 4)
					setStep: 2 2
					scratch: 0
				)
			)
			((and (< 128 y) (< y 135) (not (== loop 5))) (self setLoop: 5 scratch: 999))
			((and (> y 135) (self isNotHidden:)) (self hide:))
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DO
					(if (not case)
						(self
							setMotion: 0
							setCycle: 0
							setCel: 0
							setLoop: 6
							case: 4
						)
						(return TRUE)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance door of Prop
	(properties
		noun N_DOOR
		modNum 800
		x 127
		y 64
		priority 100
		fixPriority 1
		view 803
		cel 1
		signal (| ignrAct canUpdate) ;$4001
	)
)

(instance buttonView of View
	(properties
		modNum 800
		view 800
	)
	
	(method (doit)
		(if scratch
			(if (< (Abs (- gameTime scratch)) 25)
				0
			else
				(self setCel: 0 scratch: 0)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DO
					(= scratch gameTime)
					(if (== noun 12)
						(theBeep number: 916 play:)
					else
						(theBeep number: 921 play:)
					)
					(self setCel: 1)
					(buttonCode doit: self)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance rogerReplicator of View
	(properties
		noun N_ROGER_REPLICATOR
		modNum 800
		x 113
		y 75
		view 802
	)
)

(instance titleScreen of View
	(properties
		noun N_SCREEN
		modNum 800
		x 131
		y 25
		view 800
		loop 15
	)
)

(instance theBjornChow of Actor
	(properties
		noun N_BJORN_CHOW
		modNum 800
		x 160
		y 84
		priority 68
		fixPriority 1
		view 803
		loop 3
		yStep 1
		signal (| ignrAct fixedLoop canUpdate) ;$4801
		xStep 1
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_DO
					(ego get: iBjornChow)
					(Bset fGotChow)
					(Bclr fChowReady)
					(self dispose:)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance bg of View
	(properties
		x 127
		y 65
		priority 65
		fixPriority 1
		view 803
		loop 1
		signal (| ignrAct canUpdate)	;$4001
	)
)

(instance sparkle of Prop
	(properties
		x 127
		y 65
		priority 70
		fixPriority 1
		view 804
		signal (| ignrAct canUpdate)	;$4001
		cycleSpeed 10
	)
)

(instance light of Feature
	(properties
		modNum 800
		sightAngle 40
		x 92
		y 6
		;noun N_LIGHT	;EO: This was not in the decompiled script for some reason
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 87 5 111 10 202 10 226 5 224 4 92 4
					yourself:
				)
		)
		(super init:)
	)
)

(instance leftVents of Feature
	(properties
		noun N_LEFT_VENTS
		modNum 800
		sightAngle 40
		x 98
		y 70
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 97 10 102 12 102 101 97 103
					yourself:
				)
		)
		(super init:)
	)
)

(instance rightVents of Feature
	(properties
		noun N_RIGHT_VENTS
		modNum 800
		sightAngle 40
		x 213
		y 70
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 212 11 217 12 217 103 214 101
					yourself:
				)
		)
		(super init:)
	)
)

(instance access1 of Feature
	(properties
		noun N_ACCESS1
		modNum 800
		sightAngle 40
		x 113
		y 117
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 94 110 86 123 88 125 108 126 113 110
					yourself:
				)
		)
		(super init:)
	)
)

(instance access2 of Feature
	(properties
		noun N_ACCESS2
		modNum 800
		sightAngle 40
		x 129
		y 111
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 117 109 115 114 129 114 130 110
					yourself:
				)
		)
		(super init:)
	)
)

(instance access3 of Feature
	(properties
		noun N_ACCESS3
		modNum 800
		sightAngle 40
		x 128
		y 120
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 115 117 112 125 128 125 129 117
					yourself:
				)
		)
		(super init:)
	)
)

(instance access4 of Feature
	(properties
		noun N_ACCESS4
		modNum 800
		sightAngle 40
		x 220
		y 120
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 206 110 210 125 224 125 228 123 220 110
					yourself:
				)
		)
		(super init:)
	)
)

(instance blackPanel of Feature
	(properties
		noun N_BLACK_PANEL
		modNum 800
		sightAngle 40
		x 201
		y 111
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 133 110 131 126 205 125 201 109
					yourself:
				)
		)
		(super init:)
	)
)

(instance screen of Feature
	(properties
		noun N_SCREEN
		modNum 800
		sightAngle 40
		x 185
		y 38
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 129 20 123 26 124 49 128 53 185 54 190 49 190 24 185 21
					yourself:
				)
		)
		(super init:)
	)
)

(instance pipeL of Feature
	(properties
		noun N_LEFT_PIPE
		modNum 800
		sightAngle 40
		x 111
		y 71
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 103 9 103 105 112 104 111 10
					yourself:
				)
		)
		(super init:)
	)
)

(instance pipeR of Feature
	(properties
		noun N_RIGHT_PIPE
		modNum 800
		sightAngle 40
		x 212
		y 71
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 203 10 203 103 212 105 212 8
					yourself:
				)
		)
		(super init:)
	)
)

(instance bullethole of Feature
	(properties
		noun N_BULLET_HOLE
		modNum 800
		sightAngle 40
		x 160
		y 1000
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 160 85 156 85 157 90 160 90
					yourself:
				)
		)
		(super init:)
	)
)

(instance theExitCursor of Cursor
	(properties
		view 954
		loop 4
	)
)

(instance inCursor of Cursor
	(properties)
)

(instance theBeep of Sound
	(properties
		number 921
	)
)

(instance buttonCode of Code
	(properties)
	
	(method (doit param1)
		(switch param1
			(button0
				(if (< numIntegers 7) (addANumber doit: 0))
			)
			(button1
				(if (< numIntegers 7) (addANumber doit: 1))
			)
			(button2
				(if (< numIntegers 7) (addANumber doit: 2))
			)
			(button3
				(if (< numIntegers 7) (addANumber doit: 3))
			)
			(button4
				(if (< numIntegers 7) (addANumber doit: 4))
			)
			(button5
				(if (< numIntegers 7) (addANumber doit: 5))
			)
			(button6
				(if (< numIntegers 7) (addANumber doit: 6))
			)
			(button7
				(if (< numIntegers 7) (addANumber doit: 7))
			)
			(button8
				(if (< numIntegers 7) (addANumber doit: 8))
			)
			(button9
				(if (< numIntegers 7) (addANumber doit: 9))
			)
			(newButtonView_6
				(if
				(and (newStr compare: bjornChowCode) (not (Btst fChowReady)))
					(if (and (not (ego has: iBjornChow)) (not (Btst fGotChow)))
						(curRoom setScript: sFoodArrives)
					else
						(messager say: N_FOOD_REPLICATOR_INSET V_DO C_CHOW_READY 0 0 800)
					)
				)
			)
			(newButtonView_5)
			(starButton)
			(backspaceButton
				(if (> numIntegers 0)
					((newIntArray at: (- numIntegers 1)) dispose:)
					(-- numIntegers)
					(newStr at: numIntegers 0)
					(if
					(and (not (titleScreen isNotHidden:)) (== numIntegers 0))
						(titleScreen show:)
					)
				)
			)
			(newButtonView_4
				(= numIntegers numIntegers)
				(while (> numIntegers 0)
					(newStr at: (- numIntegers 1) 0)
					((newIntArray at: (- numIntegers 1)) dispose:)
					(-- numIntegers)
				)
				(titleScreen show:)
			)
		)
	)
)

(instance addANumber of Code
	(properties)
	
	(method (doit param1 &tmp temp0)
		(if (titleScreen isNotHidden:) (titleScreen hide:))
		(++ numIntegers)
		(= temp0 (String format: {%d} param1))
		(newStr cat: temp0)
		(temp0 dispose:)
		(newIntArray
			at:
				(- numIntegers 1)
				((View new:)
					view: 801
					setCel: param1
					init:
					posn: (+ (* (- numIntegers 1) 8) 131) 35
					yourself:
				)
		)
	)
)
