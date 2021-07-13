;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use n316)
(use EgoDead)
(use InitAllFeatures)
(use SolvePuzzle)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	captsQuartersRm 0
	bathDoor 1
	captain 2
	entryDoor 3
	case 4
	safeDoor 5
	caseView 6
	bkGrnd 7
	manila 8
	waitForEgoScript 9
)

(local
	[keyBuf 5]
	talkedToDoc
	docTimer =  100
	[caseBuf 5]
	doorIsOpen
	local13
)
(instance captsQuartersRm of Room
	(properties
		picture 30
		east 25
		vanishingX 190
		vanishingY 36
	)
	
	(method (init)
		(super init:)
		(LoadMany VIEW 30 133 134 230 232 430)
		(HandsOff)
		(ego observeControl: cBLUE)
		(safeDoor init: stopUpd:)
		(bathDoor init: ignoreActors: TRUE)
		(self
			setRegions: 314
			setFeatures:
				closet
				bunk
				desk
				bookshelf
				nightstand
				table
				couch
				((Clone couch)
					x: 235
					y: 158
					z: 0
					nsLeft: 211
					nsTop: 141
					nsRight: 259
					nsBottom: 176
					yourself:
				)
		)
		(entryDoor init: setCel: 0 ignoreActors: TRUE stopUpd:)
		(cond 
			((not (& (subMarine roomFlags?) $0001))
				(RemoveInvItems curRoomNum 0)
				(self setScript: (ScriptID 200 0))
				(addToPics add: chairPV doit:)
			)
			((not (& (subMarine roomFlags?) $0002))
				(ego
					view: 232
					posn: 293 119
					setCycle: Walk
					loop: 1
					cel: 7
					init:
					setMotion: MoveTo 280 119
				)
				(addToPics add: chairPV doit:)
				(HandsOn)
			)
			(else
				(Load VIEW 130)
				(ego
					view: 232
					posn: 290 119
					setCycle: Walk
					loop: 1
					init:
					setMotion: MoveTo 280 119
				)
				(doc init:)
				(captain
					view: 130
					loop: 1
					setPri: 15
					illegalBits: 0
					ignoreActors: TRUE
					posn: 52 150
					init:
					stopUpd:
				)
				(HandsOn)
			)
		)
		(addToPics
			add: blackHawkPV shipPV plantPV
			eachElementDo: #init
			doit:
		)
		(InitAllFeatures)
	)
	
	(method (dispose)
		(subMarine roomFlags: (| (subMarine roomFlags?) $0001))
		(ego ignoreControl: cBLUE)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,scene]')
				(Print 30 0)
			)
			((Said 'address')
				(Print 30 1)
			)
		)
	)
)

(instance waitForEgoScript of Script
	
	(method (doit)
		(super doit:)
		(if (and doorIsOpen (<= state 1))
			(self dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== howFast slow)
					(= seconds 120)
				else
					(= seconds 30)
				)
			)
			(1
				(HandsOff)
				(entryDoor setCycle: EndLoop self)
			)
			(2
				(captain setMotion: capOutMoveTo 252 122 self)
			)
			(3
				(Print 30 2)
				(captain
					setMotion: MoveTo (+ (entryDoor x?) 10) 122 self
				)
			)
			(4
				(HandsOn)
				(entryDoor setCycle: BegLoop)
				(captain
					setMotion: capOutMoveTo (+ (captain x?) 10) (captain y?)
				)
				(= seconds 15)
			)
			(5
				(HandsOff)
				(entryDoor setCycle: EndLoop self)
			)
			(6
				(captain setMotion: MoveTo 252 122 self)
			)
			(7
				(Print 30 3)
				(captain
					setMotion: MoveTo (+ (entryDoor x?) 10) 122 self
				)
			)
			(8
				(entryDoor setCycle: BegLoop self)
			)
			(9
				(EgoDead 7 0 0 30 4)
			)
		)
	)
)

(instance capOutMoveTo of MoveTo

	(method (doit)
		(super doit:)
		(if (client isBlocked:)
			(self moveDone:)
		)
	)
)

(instance openSafeScript of Script

	(method (dispose)
		(super dispose:)
		(= start 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(case illegalBits: 0 init:)
				(client setCycle: EndLoop self)
				(SolvePuzzle subMarine #pointFlag1 $0004 1)
			)
			(1
				(ego setMotion: MoveTo 198 115 self)
			)
			(2
				(ego heading: 0)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 5)
			)
			(3
				(case hide:)
				(ego view: 230 setLoop: 0 setMotion: MoveTo 213 130 self)
			)
			(4
				(ego setMotion: MoveTo 213 147 self)
			)
			(5
				(ego view: 232 heading: 90)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 5)
			)
			(6
				(case
					view: 134
					setLoop: 3
					setCel: 0
					posn: 227 131
					setPri: 11
					show:
					setMotion: MoveTo 237 131
				)
				(User canInput: TRUE)
			)
			(7
				(case setCycle: EndLoop self)
				(SolvePuzzle subMarine #pointFlag1 $0008 1)
			)
			(8
				(caseView setPri: 14 init: stopUpd:)
				(bkGrnd setPri: 13 init: stopUpd:)
				(= start state)
			)
			(9
				(HandsOff)
				(slot setPri: 15 init: cycleSpeed: 2 setCycle: EndLoop self)
			)
			(10
				(if ((inventory at: iIDCard) cel?)
					(= cycles 30)
				else
					(SolvePuzzle subMarine #pointFlag1 $0010 1)
					(case setScript: ficheScript)
					(self dispose:)
				)
			)
			(11
				(slot setCycle: BegLoop self)
			)
			(12
				(Print 30 5)
				(User canInput: TRUE)
				(self init:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'open/briefcase')
				(if (not (case cel?))
					(Print 30 6 #edit @caseBuf 12)
					(cond 
						((not (StrCmp @caseBuf {762134}))
							(self cue:)
						)
						((not (StrCmp @caseBuf {}))
							(return)
						)
						(else
							(Print 30 7)
						)
					)
				else
					(Print 30 8)
				)
			)
			((Said 'close/briefcase')
				(if (case cel?)
					(if (not (ego has: iIDCard))
						(Print 30 9)
						(slot dispose:)
					)
					(HandsOff)
					(case setScript: closeCaseScript)
				else
					(Print 30 10)
				)
			)
			(
				(or
					(Said 'get/briefcase')
					(Said 'adjust/briefcase/safe')
				)
				(HandsOff)
				(case setScript: closeCaseScript)
			)
			((Said 'look[<at]/top')
				(if (case cel?)
					(Print 30 11)
				else
					(Print 30 10)
				)
			)
			((Said 'open/top,compartment')
				(if (case cel?)
					(Print 30 12)
				else
					(Print 30 10)
				)
			)
			((Said 'adjust,insert,use/id,card[<id][/slot,hole]')
				(cond 
					((not (case cel?))
						(event claimed: FALSE)
					)
					((ego has: iIDCard)
						(HandsOff)
						(Print 30 13)
						(ego put: iIDCard curRoom)
						(self cue:)
					)
					(else
						(Print 30 14)
					)
				)
			)
			((Said 'get,get/id,card[<id]')
				(cond 
					((ego has: iIDCard)
						(Print 30 15)
					)
					((IsInvItemInRoom curRoomNum iIDCard)
						(slot dispose:)
						(Print 30 16)
						(ego get: iIDCard)
						(self init:)
					)
					(else
						(CantSee)
					)
				)
			)
			((Said 'look,read[<at]/order')
				(Print 30 17)
			)
		)
	)
)

(instance ficheScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(caseView setCycle: EndLoop self)
			)
			(1 (= seconds 2))
			(2
				(User canInput: TRUE)
				(Print 30 18)
			)
			(3
				(HandsOff)
				(codeView dispose:)
				(caseView setCycle: BegLoop self)
			)
			(4 (slot setCycle: BegLoop self))
			(5
				(slot dispose:)
				(Print 30 19)
				(ego get: iIDCard)
				(if (IsInvItemInRoom curRoomNum iMicrofilm)
					(Print 30 20)
					(ego get: iMicrofilm)
				)
				(HandsOff)
				(case setScript: closeCaseScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'examine,look[<at,in]/briefcase')
				(Print 30 21)
			)
			((Said 'use/viewer')
				(if (ego has: iMicrofilm)
					(Print 30 22)
				else
					(Print 30 23)
				)
			)
			((Said 'insert/film')
				(cond 
					((ego has: iMicrofilm)
						(Print 30 24)
						(ego put: iMicrofilm curRoom)
						(codeView setPri: 15 init:)
						(Animate (cast elements?) FALSE)
						(Print 30 25)
					)
					((IsInvItemInRoom curRoomNum iMicrofilm)
						(Print 30 26)
					)
					(else
						(Print 30 27)
					)
				)
			)
			(
				(or
					(Said 'get,get/id,(card[<id])')
					(Said 'close/briefcase')
				)
				(self cue:)
			)
			((Said 'look,read[<at]/order')
				(Print 30 17)
			)
		)
	)
)

(instance closeCaseScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (case cel?)
					(case setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(1
				(caseView dispose:)
				(bkGrnd dispose:)
				(case setMotion: MoveTo 227 131 self)
			)
			(2
				(case hide:)
				(ego view: 230 setLoop: 1 setMotion: MoveTo 213 130 self)
			)
			(3
				(ego setMotion: MoveTo 198 115 self)
			)
			(4
				(ego
					view: 232
					loop: 3
					setLoop: -1
					setMotion: MoveTo 222 115 self
				)
				(case
					view: 430
					loop: 0
					cel: 3
					setPri: -1
					posn: 195 87
					show:
					stopUpd:
				)
			)
			(5
				(safeDoor setCycle: BegLoop self)
			)
			(6
				(HandsOn)
				(safeDoor setScript: 0)
				(safeDoor stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance captain of Actor
	(properties
		view 133
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((> x (entryDoor x?)))
			((Said '[/captain]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(if (& (subMarine roomFlags?) $0002)
							(Print 30 28)
						else
							(Print 30 29)
						)
					)
					((not (& (subMarine roomFlags?) $0002)))
					((Said 'address') (Print 30 30) (Print 30 31))
				)
			)
		)
	)
)

(instance doc of Prop
	(properties
		y 147
		x 70
		view 130
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((!= docTimer 0)
				(-- docTimer)
			)
			((not (self cel?))
				(self setCycle: EndLoop)
				(= docTimer (Random 100 300))
			)
			(else (self setCycle: BegLoop)
				(= docTimer (Random 100 300))
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/doc,man]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 32)
					)
					((Said 'address')
						(if (not talkedToDoc)
							(= talkedToDoc TRUE)
							(Print 30 33)
							(Print 30 34)
						else
							(Print 30 35)
						)
					)
				)
			)
		)
	)
)

(instance bkGrnd of View
	(properties
		y 88
		x 246
		view 430
		loop 5
	)
)

(instance manila of View
	(properties
		y 78
		x 246
		view 430
		cel 2
	)
)

(instance caseView of Prop
	(properties
		y 85
		x 246
		view 430
		loop 1
	)
	
	(method (handleEvent event)
		(case handleEvent: event)
	)
)

(instance slot of Prop
	(properties
		y 50
		x 233
		view 430
		loop 2
	)
)

(instance codeView of View
	(properties
		y 64
		x 246
		view 430
		loop 4
	)
)

(instance blackHawkPV of RPicView
	(properties
		y 71
		x 215
		view 30
	)
)

(instance shipPV of RPicView
	(properties
		y 113
		x 21
		view 30
		cel 1
		priority 9
	)
)

(instance chairPV of RPicView
	(properties
		y 130
		x 84
		view 30
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/chair]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 36)
					)
					((Said 'sit')
						(Print 30 37)
					)
					((Said 'stand')
						(Print 30 38)
					)
				)
			)
		)
	)
)

(instance plantPV of RPicView
	(properties
		y 116
		x 251
		view 30
		cel 3
	)
)

(instance safeDoor of Prop
	(properties
		y 91
		x 199
		view 30
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/safe]>')
				(cond 
					((TurnIfSaid self event 'look[<at,in]/*'))
					((Said 'look<in')
						(if (not cel)
							(Print 30 39)
						else
							(Print 30 40)
						)
					)
					((Said 'look[<at]')
						(Print 30 41)
					)
					((GoToIfSaid self event 222 115 'open' 30 42))
					((Said 'open')
						(if (safeDoor cel?)
							(Print 30 8)
						else
							(User canInput: TRUE canControl: FALSE)
							(keyPad init:)
						)
					)
				)
			)
			((Said '[/code,keypad]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 43)
					)
					((GoToIfSaid self event 222 115 'enter/code' 30 42))
					((Said 'enter')
						(if (safeDoor cel?)
							(Print 30 8)
						else
							(User canInput: TRUE canControl: FALSE)
							(keyPad init:)
						)
					)
				)
			)
		)
	)
)

(instance bathDoor of Prop
	(properties
		y 116
		x 40
		view 30
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 44)
					)
					((Said 'close')
						(ItIs)
					)
					(
						(and
							(> (ego x?) x)
							(GoToIfSaid self event stupidAvoider 3 'open' 30 42)
						)
					)
					((and (<= (ego x?) x) (Said 'open'))
						(self setScript: bathDoorScript)
					)
					((Said 'open')
						(self setScript: bathDoorScript)
					)
				)
			)
			((Said 'exit[/bathroom]')
				(if (<= (ego x?) x)
					(self setScript: bathDoorScript)
				else
					(Print 30 45)
				)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance bathDoorScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client setCycle: EndLoop self)
			)
			(1
				(= register (> (ego x?) (client x?)))
				(ego
					illegalBits: 0
					setMotion: MoveTo (if register 22 else 67) 117 self
				)
			)
			(2
				(client setCycle: BegLoop client)
				(ego observeControl: cBLUE cWHITE)
				(if (< (ego x?) (client x?))
					(ego setScript: (ScriptID 303 0))
					(client setScript: 0)
					(HandsOn)
				else
					(ego setScript: 0)
					(= cycles 5)
				)
			)
			(3
				(DisposeScript 303)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance entryDoor of Prop
	(properties
		y 115
		x 283
		view 30
		loop 3
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== (ego onControl: origin) cGREEN)
				(not (self script?))
				(= doorIsOpen TRUE)
			)
			(HandsOff)
			(self setScript: entryDoorScript)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 46)
					)
					((Said 'close')
						(ItIs)
					)
					((< (ego x?) (bathDoor x?)))
					((GoToIfSaid self event 267 125 'open' 30 42)
						(= doorIsOpen TRUE)
					)
					((Said 'open')
						(= doorIsOpen TRUE)
						(HandsOff)
						(self setScript: entryDoorScript)
					)
				)
			)
		)
	)
)

(instance entryDoorScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: EndLoop)
				(= cycles 7)
			)
			(1
				(if (> (ego x?) (client x?))
					(= register -1)
				else
					(= register 1)
				)
				(ego
					illegalBits: (if (== register 1) 0 else (| cWHITE cBLUE))
					setMotion: MoveTo (+ (ego x?) (* 35 register)) (ego y?) self
				)
			)
			(2
				(ego illegalBits: (| cWHITE cBLUE))
				(client setCycle: BegLoop self)
			)
			(3
				(if (== register 1)
					(curRoom newRoom: 25)
				else
					(if (not (curRoom script?))
						(HandsOn)
					)
					(client stopUpd:)
					(= doorIsOpen 0)
					(self dispose:)
				)
			)
		)
	)
)

(instance case of Actor
	(properties
		y 87
		x 195
		view 430
		cel 3
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(Said '[/briefcase]>')
					(Said 'examine,look[<at,in]')
				)
				(cond 
					((== (case script?) ficheScript)
						(Print 30 21)
					)
					((& (subMarine roomFlags?) $0001)
						(if (case cel?)
							(Print 30 47)
							(Print 30 48)
							(Print 30 49)
							(Print 30 50)
						else
							(Print 30 51)
						)
					)
					((not (safeDoor cel?))
						(CantSee)
					)
					((not (cast contains: caseView))
						(Print 30 21)
					)
					(else
						(if (IsInvItemInRoom curRoomNum 0)
							(Print 30 52)
						else
							(Print 30 53)
						)
						(Print 30 48)
						(Print 30 49)
						(Print 30 50)
					)
				)
			)
		)
	)
)

(instance keyPad of View
	(properties
		y 176
		x 291
		view 30
		cel 4
	)
	
	(method (init)
		(self signal: (| staticView stopUpdOn))
		(User mapKeyToDir: FALSE)
		(oneBut init: @keyBuf)
		(twoBut init: @keyBuf)
		(threeBut init: @keyBuf)
		(fourBut init: @keyBuf)
		(fiveBut init: @keyBuf)
		(sixBut init: @keyBuf)
		(sevenBut init: @keyBuf)
		(eightBut init: @keyBuf)
		(nineBut init: @keyBuf)
		(zeroBut init: @keyBuf)
		(enterBut init:)
		(super init:)
	)
	
	(method (dispose)
		(User mapKeyToDir: TRUE)
		(oneBut dispose:)
		(twoBut dispose:)
		(threeBut dispose:)
		(fourBut dispose:)
		(fiveBut dispose:)
		(sixBut dispose:)
		(sevenBut dispose:)
		(eightBut dispose:)
		(nineBut dispose:)
		(zeroBut dispose:)
		(enterBut dispose:)
		(super dispose:)
	)
)

(class KeyPadButton of RFeature
	(properties
		theString 0
		strToCat 0
		keyEquiv 0
		maxLength 0
	)
	
	(method (init theTheString)
		(= theString theTheString)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (doit)
		(if (< (StrLen theString) maxLength)
			(StrCat theString strToCat)
			(Display {88888888}
				p_at 275 118
				p_font 30
				p_color vGREY
			)
			(Display theString
				p_at (+ 275 (* 4 (- 8 (StrLen theString)))) 118
				p_font 30
				p_color vLRED
			)
		)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(keyDown
				(if (== (event message?) keyEquiv)
					(self doit:)
					(event claimed: TRUE)
				)
			)
			(mouseDown
				(if (MousedOn self event)
					(self doit:)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance oneBut of KeyPadButton
	(properties
		nsTop 128
		nsLeft 275
		nsBottom 136
		nsRight 283
		strToCat {1}
		keyEquiv `1
		maxLength 8
	)
)

(instance twoBut of KeyPadButton
	(properties
		nsTop 128
		nsLeft 286
		nsBottom 136
		nsRight 294
		strToCat {2}
		keyEquiv `2
		maxLength 8
	)
)

(instance threeBut of KeyPadButton
	(properties
		nsTop 128
		nsLeft 299
		nsBottom 136
		nsRight 307
		strToCat {3}
		keyEquiv `3
		maxLength 8
	)
)

(instance fourBut of KeyPadButton
	(properties
		nsTop 140
		nsLeft 275
		nsBottom 148
		nsRight 283
		strToCat {4}
		keyEquiv `4
		maxLength 8
	)
)

(instance fiveBut of KeyPadButton
	(properties
		nsTop 140
		nsLeft 286
		nsBottom 148
		nsRight 294
		strToCat {5}
		keyEquiv `5
		maxLength 8
	)
)

(instance sixBut of KeyPadButton
	(properties
		nsTop 140
		nsLeft 299
		nsBottom 148
		nsRight 307
		strToCat {6}
		keyEquiv `6
		maxLength 8
	)
)

(instance sevenBut of KeyPadButton
	(properties
		nsTop 152
		nsLeft 275
		nsBottom 160
		nsRight 283
		strToCat {7}
		keyEquiv `5
		maxLength 8
	)
)

(instance eightBut of KeyPadButton
	(properties
		nsTop 152
		nsLeft 286
		nsBottom 160
		nsRight 294
		strToCat {8}
		keyEquiv `8
		maxLength 8
	)
)

(instance nineBut of KeyPadButton
	(properties
		nsTop 152
		nsLeft 299
		nsBottom 160
		nsRight 307
		strToCat {9}
		keyEquiv `9
		maxLength 8
	)
)

(instance zeroBut of KeyPadButton
	(properties
		nsTop 164
		nsLeft 299
		nsBottom 172
		nsRight 307
		strToCat {0}
		keyEquiv `0
		maxLength 8
	)
)

(instance enterBut of RFeature
	(properties
		y 285
		x 168
		nsTop 164
		nsLeft 275
		nsBottom 172
		nsRight 295
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(super dispose:)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(and
						(== (event type?) keyDown)
						(== (event message?) ENTER)
					)
					(MousedOn self event)
				)
				(if (StrCmp @keyBuf {23448803})
					(Print 30 54)
					(HandsOn)
					(keyPad dispose:)
					(= keyBuf 0)
				else
					(keyPad dispose:)
					(= keyBuf 0)
					(safeDoor setScript: openSafeScript)
				)
			)
		)
	)
)

(instance closet of RFeature
	(properties
		y 83
		x 128
		nsTop 54
		nsLeft 105
		nsBottom 113
		nsRight 152
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/closet,cabinet]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 55)
					)
					((Said 'open')
						(Print 30 56)
					)
					((Said 'close')
						(ItIs)
					)
				)
			)
		)
	)
)

(instance bunk of RFeature
	(properties
		y 153
		x 61
		nsTop 138
		nsLeft 10
		nsBottom 169
		nsRight 112
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look[<at]/bunk,bed'))
			((Said 'look[<at][/bunk,bed]')
				(Print 30 57)
			)
			((Said 'sit')
				(Print 30 37)
			)
		)
	)
)

(instance couch of RFeature
	(properties
		y 144
		x 192
		nsTop 113
		nsLeft 174
		nsBottom 176
		nsRight 210
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/couch]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 58)
					)
					((Said 'sit')
						(Print 30 37)
					)
				)
			)
		)
	)
)

(instance table of RFeature
	(properties
		y 132
		x 244
		nsTop 124
		nsLeft 222
		nsBottom 140
		nsRight 267
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/table]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 59)
					)
				)
			)
		)
	)
)

(instance bookshelf of RFeature
	(properties
		y 64
		x 79
		nsTop 54
		nsLeft 54
		nsBottom 74
		nsRight 105
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/bookcase,shelf]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 60)
					)
					((Said 'open')
						(Print 30 61)
					)
					((Said 'close')
						(ItIs)
					)
				)
			)
		)
	)
)

(instance desk of RFeature
	(properties
		y 99
		x 79
		nsTop 87
		nsLeft 54
		nsBottom 112
		nsRight 105
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/desk]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 62)
					)
					((Said 'open')
						(Print 30 61)
					)
					((Said 'close')
						(ItIs)
					)
				)
			)
			((Said '[/drawer]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 63)
					)
					((Said 'open')
						(Print 30 61)
					)
					((Said 'close')
						(ItIs)
					)
				)
			)
		)
	)
)

(instance nightstand of RFeature
	(properties
		y 127
		x 42
		nsTop 117
		nsLeft 27
		nsBottom 137
		nsRight 58
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/dresser]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 30 64)
					)
					((Said 'open')
						(Print 30 61)
					)
					((Said 'close')
						(ItIs)
					)
				)
			)
		)
	)
)

(instance stupidAvoider of Feature
	(properties
		y 117
		x 67
	)
)
