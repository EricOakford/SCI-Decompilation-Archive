;;; Sierra Script 1.0 - (do not remove this comment)
(script# DUNE)
(include game.sh)
(use Main)
(use Intrface)
(use Chase)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	regDune 0
	DUNELOOPER 1
	proc501_2 2
	proc501_3 3
)

(local
	local0
	local1
	local2
	local3
	twistSound
	local5
	scorpion
	[local7 12]
)
(procedure (proc501_2 param1 &tmp temp0)
	(return
		(if
		(& (= temp0 (OnControl 2 (ego x?) (ego y?))) param1)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(procedure (proc501_3 param1 &tmp temp0)
	(= temp0 0)
	(while param1
		(= param1 (>> param1 $0001))
		(++ temp0)
	)
	(return (- temp0 1))
)

(class AView of View
	
	(method (delete)
		(= signal (& signal $ffdf))
		(super delete:)
	)
)

(instance footPrint of AView
	(properties)
)

(class dMoveTo of Motion
	
	(method (init theClient theX theY theCaller &tmp [temp0 2])
		(= client theClient)
		(= x theX)
		(if (!= (ego looper?) 0)
			(= y (+ (ego y?) (- (ego y?) theY)))
		else
			(= y theY)
		)
		(if (== argc 4) (= caller theCaller))
		(= b-moveCnt 0)
		(theClient
			heading: (GetAngle (theClient x?) (theClient y?) x y)
		)
		(if (== global104 1)
			(cond 
				(
				(or (< (ego heading?) 45) (> (ego heading?) 315)) (ego loop: 2))
				(
				(and (>= (ego heading?) 45) (< (ego heading?) 135)) (ego loop: 0))
				(
				(and (>= (ego heading?) 135) (< (ego heading?) 225)) (ego loop: 3))
				(else (ego loop: 1))
			)
		else
			(DirLoop theClient (theClient heading?))
		)
		(InitBresen self)
	)
)

(instance regDune of Region
	(properties)
	
	(method (init)
		(Load VIEW 777)
		(Load VIEW 82)
		(Load VIEW 0)
		(Load VIEW 68)
		(Load VIEW 106)
		(Load VIEW 108)
		(User mapKeyToDir: FALSE)
		(super init:)
	)
	
	(method (dispose)
		(User mapKeyToDir: TRUE)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp eventType egoX egoY temp3 temp4 theGPEventMessage)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (User controls?) TRUE)
				(= eventType (event type?))
				(MapKeyToDir event)
				(switch (event type?)
					(mouseDown
						(if (and (curRoom controls?) (IsObject ego))
							(= gGEgoY_4 (event y?))
							(= gGEgoX_5 (event x?))
							(= gGEgoY_5 (ego y?))
							(ego setMotion: dMoveTo (event x?) (event y?))
							(User prevDir: 0)
							(event claimed: TRUE)
						)
					)
					(direction
						(if (and (curRoom controls?) (IsObject ego))
							(= theGPEventMessage (event message?))
							(= gGEgoX_5 0)
							(= gGEgoY_4 0)
							(if
								(and
									(== eventType keyDown)
									(== gPEventMessage theGPEventMessage)
									(IsObject (ego mover?))
								)
								(= theGPEventMessage 0)
							)
							(= gPEventMessage theGPEventMessage)
							(if (== theGPEventMessage 0)
								(ego setMotion: 0)
								(return (event claimed: TRUE))
							)
							(if (ego mover?)
								(if
									(==
										theGPEventMessage
										(cond 
											(
											(or (== global104 0) (== global104 4) (== global104 2))
												(cond 
													((== (ego heading?) 0) 1)
													((< (ego heading?) 90) 2)
													((== (ego heading?) 90) 3)
													((< (ego heading?) 180) 4)
													((== (ego heading?) 180) 5)
													((< (ego heading?) 270) 6)
													((== (ego heading?) 270) 7)
													(else 8)
												)
											)
											((== (ego heading?) 180) 1)
											((< (ego heading?) 90) 4)
											((== (ego heading?) 90) 3)
											((< (ego heading?) 180) 2)
											((== (ego heading?) 0) 5)
											((< (ego heading?) 270) 8)
											((== (ego heading?) 270) 7)
											(else 6)
										)
									)
									(return (event claimed: 1))
								)
							)
							(= egoX (ego x?))
							(= egoY (ego y?))
							(= temp3 (* (ego xStep?) 400))
							(= temp4 (* (ego yStep?) 400))
							(if
								(and
									(!= global104 0)
									(!= global104 4)
									(!= global104 2)
								)
								(= temp4 (* temp4 -1))
							)
							(switch theGPEventMessage
								(1 (= egoY (- egoY temp4)))
								(2
									(= egoX (+ egoX temp3))
									(= egoY (- egoY temp4))
								)
								(3 (= egoX (+ egoX temp3)))
								(4
									(= egoX (+ egoX temp3))
									(= egoY (+ egoY temp4))
								)
								(5 (= egoY (+ egoY temp4)))
								(6
									(= egoX (- egoX temp3))
									(= egoY (+ egoY temp4))
								)
								(7 (= egoX (- egoX temp3)))
								(8
									(= egoX (- egoX temp3))
									(= egoY (- egoY temp4))
								)
							)
							(ego setMotion: MoveTo egoX egoY)
							(event claimed: TRUE)
						)
					)
					(saidEvent
						(cond 
							((Said 'look>')
								(cond 
									((Said '/desert') (Print 501 0))
									((Said '/dirt,dirt') (Print 501 1))
									((Said '/dune') (Print 501 2))
									((or (Said '/dirt') (Said '<down')) (Print 501 3))
									((Said '/lightning') (Print 501 4))
									((or (Said '/cloud,air') (Said '<up')) (Print 501 5))
									((Said '/down') (Print 501 6))
									((Said '/bug')
										(if (cast contains: scorpion)
											(Print 501 7)
										else
											(Print 501 8)
										)
									)
									((Said '/butte,toe') (Print 501 9))
									((Said '/footprint')
										(if (cast contains: terminator)
											(Print 501 10)
										else
											(Print 501 11)
										)
									)
									((Said '/rock') (Print 501 12))
									((Said '/cloud') (Print 501 13))
									((Said '/butte') (Print 501 14))
								)
							)
							((Said 'get>')
								(cond 
									((Said '/dirt') (Print 501 15))
									((Said '/rock') (Print 501 16))
									((Said '/bug')
										(if (cast contains: scorpion)
											(Print 501 17)
										else
											(Print 501 8)
										)
									)
								)
							)
							((Said 'conceal') (Print 501 18))
							((Said 'dig') (Print 501 19))
							((or (Said 'attack/bug') (Said 'stair/bug')) (if (cast contains: scorpion) (Print 501 20)))
							((Said 'climb,sit,crawl,lie') (Print 501 21))
							((Said 'converse/android')
								(if (cast contains: terminator)
									(Print 501 22)
								else
									(Print 501 23)
								)
							)
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
	)
	
	(method (notify param1)
		(switch param1
			(1
				(terminator view: 777 setCycle: Walk setScript: tActions)
				(= local0 0)
			)
			(2 (terminator dispose:))
			(3
				(terminator
					view: 777
					setMotion: 0
					setScript: 0
					ignoreActors:
				)
			)
			(4
				(if (== (Random 1 4) 3)
					((= scorpion (ScriptID 511 0)) init:)
				)
			)
		)
	)
)

(instance DUNELOOPER of Script
	(properties)
	
	(method (doit)
		(if (or (== global104 1) (== global104 3))
			(ego
				loop:
					(cond 
						(
						(or (< (ego heading?) 45) (> (ego heading?) 315)) 2)
						(
						(and (>= (ego heading?) 45) (< (ego heading?) 135)) 0)
						(
						(and (>= (ego heading?) 135) (< (ego heading?) 225)) 3)
						(else 1)
					)
			)
		else
			(DirLoop ego (ego heading?))
		)
	)
)

(instance tActions of Script
	(properties)
	
	(method (doit)
		(asm
			lsg      curRoomNum
			lag      newRoomNum
			ne?     
			bnt      code_0a8a
			ret     
code_0a8a:
			lsg      global104
			ldi      0
			ne?     
			bnt      code_0ab6
			lsl      local0
			ldi      1
			eq?     
			bnt      code_0ab6
			ldi      7
			aTop     state
			sal      local0
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			lsg      gGEgoX_4
			lsg      gGEgoY_3
			pushSelf
			pushi    217
			pushi    1
			pushi    2
			lag      terminator
			send     18
code_0ab6:
			lsl      local0
			ldi      7
			eq?     
			bnt      code_0ae7
			pushi    #onControl
			pushi    0
			lag      terminator
			send     4
			push    
			ldi      2
			and     
			bnt      code_0ae7
			pushi    #view
			pushi    1
			pushi    777
			pushi    213
			pushi    1
			pushi    0
			lag      terminator
			send     12
			pushi    #changeState
			pushi    1
			pushi    8
			self     6
			ldi      8
			sal      local0
code_0ae7:
			lsl      local0
			ldi      8
			eq?     
			bt       code_0af7
			lsl      local0
			ldi      7
			eq?     
			bnt      code_0b2b
code_0af7:
			lsg      global104
			ldi      0
			eq?     
			bnt      code_0b2b
			pushi    #view
			pushi    1
			pushi    106
			pushi    216
			pushi    1
			pushi    2
			pushi    214
			pushi    1
			class    Avoider
			push    
			pushi    213
			pushi    4
			class    Chase
			push    
			lsg      ego
			pushi    10
			pushSelf
			lag      terminator
			send     30
			ldi      0
			aTop     seconds
			ldi      1
			aTop     state
			sal      local0
code_0b2b:
			lsl      local0
			ldi      10
			ne?     
			bnt      code_0bbb
			pushi    5
			pushi    2
			pushi    #x
			pushi    0
			lag      terminator
			send     4
			push    
			ldi      9
			sub     
			push    
			pushi    #y
			pushi    0
			lag      terminator
			send     4
			push    
			ldi      3
			sub     
			push    
			pushi    #x
			pushi    0
			lag      terminator
			send     4
			push    
			ldi      9
			add     
			push    
			pushi    #y
			pushi    0
			lag      terminator
			send     4
			push    
			callk    OnControl,  10
			push    
			lag      global591
			and     
			bnt      code_0bbb
			lsl      local0
			ldi      1
			eq?     
			bnt      code_0ba8
			pushi    199
			pushi    #-info-
			lsl      local1
			lsl      local2
			ldi      3
			add     
			push    
			lag      terminator
			send     8
			ldi      0
			aTop     seconds
			ldi      1
			aTop     state
			pushi    #setAvoider
			pushi    1
			class    Avoider
			push    
			pushi    213
			pushi    4
			class    Chase
			push    
			lsg      ego
			pushi    10
			pushSelf
			lag      terminator
			send     18
			jmp      code_0bbb
code_0ba8:
			pushi    #setMotion
			pushi    1
			pushi    0
			pushi    205
			pushi    0
			pushi    5
			pushi    1
			pushi    777
			lag      terminator
			send     16
code_0bbb:
			pushi    3
			pushi    1
			pushi    #x
			pushi    0
			lag      terminator
			send     4
			push    
			pushi    #y
			pushi    0
			lag      terminator
			send     4
			push    
			callk    OnControl,  6
			push    
			ldi      0
			ne?     
			bnt      code_0bea
			pushi    #x
			pushi    0
			lag      terminator
			send     4
			sal      local1
			pushi    #y
			pushi    0
			lag      terminator
			send     4
			sal      local2
code_0bea:
			lsl      local0
			ldi      1
			eq?     
			bt       code_0bfa
			lsl      local0
			ldi      7
			eq?     
			bnt      code_0ca5
code_0bfa:
			pushi    #cel
			pushi    0
			lag      terminator
			send     4
			push    
			ldi      0
			eq?     
			bt       code_0c32
			pushi    #loop
			pushi    0
			lag      terminator
			send     4
			push    
			ldi      2
			lt?     
			bnt      code_0c24
			pushi    #cel
			pushi    0
			lag      terminator
			send     4
			push    
			ldi      4
			eq?     
			jmp      code_0c2f
code_0c24:
			pushi    #cel
			pushi    0
			lag      terminator
			send     4
			push    
			ldi      3
			eq?     
code_0c2f:
			bnt      code_0ca5
code_0c32:
			pushi    3
			pushi    2
			pushi    #x
			pushi    0
			lag      terminator
			send     4
			push    
			pushi    #y
			pushi    0
			lag      terminator
			send     4
			push    
			callk    OnControl,  6
			sal      local3
			pushi    1
			push    
			call     proc501_3,  2
			sal      local3
			push    
			ldi      65535
			ne?     
			bnt      code_0ca5
			pushi    #view
			pushi    0
			lag      terminator
			send     4
			push    
			ldi      777
			ne?     
			bnt      code_0ca5
			pushi    #view
			pushi    1
			pushi    106
			pushi    6
			pushi    1
			pushi    #loop
			pushi    0
			lag      terminator
			send     4
			push    
			pushi    7
			pushi    1
			pushi    #cel
			pushi    0
			lag      terminator
			send     4
			push    
			pushi    205
			pushi    1
			pushi    1
			pushi    199
			pushi    2
			pushi    #x
			pushi    0
			lag      terminator
			send     4
			push    
			pushi    #y
			pushi    0
			lag      terminator
			send     4
			push    
			pushi    207
			pushi    0
			lofsa    footPrint
			send     36
code_0ca5:
			lsg      curRoomNum
			lag      newRoomNum
			ne?     
			bnt      code_0cae
			ret     
code_0cae:
			pushi    #doit
			pushi    0
			super    Script,  4
			ret     
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds notifyCountdown))
			(1
				(if (== global104 0)
					(terminator
						view: 106
						setMotion: Chase ego 10 self
						setAvoider: Avoider
						ignoreActors:
					)
					(= local0 1)
				else
					(= local0 (= state 7))
					(terminator
						view: 106
						setMotion: MoveTo gGEgoX_4 gGEgoY_3 self
						ignoreControl: 2
					)
				)
			)
			(2
				(if (!= curRoomNum newRoomNum) (return))
				(= local0 10)
				(= global116 1)
				(HandsOff)
				(terminator
					ignoreActors:
					illegalBits: 0
					posn: (ego x?) (ego y?)
				)
				(ego
					view: 106
					setLoop: 4
					cel: 255
					ignoreHorizon:
					illegalBits: 0
					setCycle: EndLoop
					setPri: (ego priority?)
					setStep: 4 4
					setMotion: MoveTo (+ (ego x?) 11) (- (ego y?) 15) self
				)
				(RedrawCast)
			)
			(3
				(ego setLoop: 5 setCycle: Forward)
				(terminator
					view: 106
					setLoop: 6
					illegalBits: 0
					setPri: (ego priority?)
					setCycle: Forward
					show:
				)
				(= seconds 2)
			)
			(4
				(terminator
					view: 106
					setLoop: 7
					setPri: (ego priority?)
					setCycle: Forward
				)
				(= seconds 4)
			)
			(5
				(ego hide:)
				((= twistSound (Sound new:))
					number: 97
					loop: -1
					priority: 99
					play:
				)
				(terminator setLoop: 8 setCycle: Forward)
				(= seconds 6)
			)
			(6
				(twistSound stop:)
				(terminator setLoop: 8 setCel: 0)
				(EgoDead 901 0 14 16)
			)
			(8 (= seconds 12) (= local0 8))
			(9
				(if (and (!= global104 1) (!= global104 0))
					(= seconds (= state 8))
				else
					(self changeState: 2)
				)
			)
		)
	)
)
