;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include game.sh)
(use Main)
(use Sleep)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm41 0
)

(local
	local0 =  1
	stoneLookCount
	local2
	frederickSays
	pierreSays
	local5
	local6
	local7
	local8
	enteringCastle
)
(instance rm41 of Room
	(properties
		picture 41
		style WIPEDOWN
	)
	
	(method (init &tmp oldEgoX oldEgoY)
		(Load VIEW vCastleGuards2)
		(cSound fade:)
		(super init: &rest)
		(StatusLine enable:)
		(= enteringCastle (== prevRoomNum 141))
		(if
		(and (Btst SAVED_BARNARD) (not enteringCastle) (not (Btst OBTAINED_BARNARD_REWARD)))
			(self horizon: 130)
		)
		(mouseDownHandler add: self)
		(self setFeatures: onTrees1 onWeed1 onCastle onStones)
		(= oldEgoX (ego x?))
		(= oldEgoY (ego y?))
		(NormalEgo)
		(ego init:)
		(if (not Night)
			(rGuard init: stopUpd:)
			(lGuard init: stopUpd:)
			(if
			(and (Btst SAVED_BARNARD) (not enteringCastle) (not (Btst OBTAINED_BARNARD_REWARD)))
				(HandsOff)
				(cSound prevSignal: 0)
				(lGuard setScript: lGuardTrumpets)
				(rGuard setScript: rGuardTrumpets)
			)
		)
		(door1 init: stopUpd:)
		(door2 init: stopUpd:)
		(switch prevRoomNum
			(38
				(if (and (Btst SAVED_BARNARD) (not (Btst OBTAINED_BARNARD_REWARD)))
					(ego posn: 43 168 setMotion: MoveTo 160 168)
				else
					(if (< oldEgoY 110) (= oldEgoY 110))
					(ego posn: 1 oldEgoY setMotion: MoveTo 15 oldEgoY)
				)
			)
			(39
				(if (and (Btst SAVED_BARNARD) (not (Btst OBTAINED_BARNARD_REWARD)))
					(ego posn: 160 186 setMotion: MoveTo 160 168)
				else
					(ego posn: oldEgoX 189)
					(cond 
						((< oldEgoX 10) (ego setMotion: MoveTo (+ oldEgoX 25) 175))
						((> oldEgoX 310) (ego setMotion: MoveTo (- oldEgoX 25) 175))
						(else (ego setMotion: MoveTo oldEgoX 175))
					)
				)
			)
			(40
				(if (and (Btst SAVED_BARNARD) (not (Btst OBTAINED_BARNARD_REWARD)))
					(ego posn: 283 168 setMotion: MoveTo 160 168)
				else
					(if (< oldEgoY 110) (= oldEgoY 110))
					(ego posn: 319 oldEgoY setMotion: MoveTo 305 oldEgoY)
				)
			)
			(141 (ego setScript: leaveHall))
			(else 
				(ego posn: 160 189 setMotion: MoveTo 160 170)
			)
		)
		(self setLocales: CASTLE)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(switch (ego edgeHit?)
			(EAST
				(= egoY (+ (/ (= temp0 (- (ego y?) 105)) 3) 98))
				(curRoom newRoom: 40)
			)
			(SOUTH
				(= egoX (+ (/ (ego x?) 3) 102))
				(curRoom newRoom: 39)
			)
			(WEST
				(= egoY (+ (/ (= temp0 (- (ego y?) 105)) 2) 84))
				(curRoom newRoom: 38)
			)
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset VISITED_CASTLE_GUARDS)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if (MouseClaimed ego event shiftDown)
					(HighPrint 41 0)
					;So you says to yourself, "Self", you says...
					)
			)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							((Said '[/!*]')
								(HighPrint 41 1)
								;You face the main doors of the Baron's keep.
								)
							((Said '/castle')
								(HighPrint 41 2)
								;The castle grounds look like no one has taken care of them.
								;The castle is still impressive close up, but it looks rather dirty
								)
							((Said '/north')
								(HighPrint 41 3)
								;You see the castle and the guards
								)
							((Said '/east')
								(HighPrint 41 4)
								;Along the wall to the southeast, you can see the stables.
								)
							((Said '/south')
								(HighPrint 41 5)
								;You see the main courtyard of the castle.
								)
							((Said '/west')
								(HighPrint 41 6)
								;Along the wall to the southwest, you can see the barracks.
								)
							((Said '/door')
								(if Night
									(HighPrint 41 7)
									;The doors are securely locked for the night.
									else
									(HighPrint 41 8)
									;The doors are guarded by Frederick and Pierre.
									)
								)
						)
					)
					((Said 'climb[/castle,wall]')
						(HighPrint 41 9)
						;The castle walls are very high.  You judge that you would not be able to climb them.
						)
					((Said 'knock,knock,force/door')
						(HighPrint 41 10)
						;There is no response.
						)
					((Said 'open,lockpick/door,hasp')
						(if Night
							(HighPrint 41 11)
							;The doors are securely locked and barred from the inside.
							else
							(HighPrint 41 8)
							;The doors are guarded by Frederick and Pierre.
							)
						)
					((or (Said 'nap') (Said 'go[<to]/nap'))
						(HighPrint 41 12)
						;You barely get to sleep when the guard on night patrol kicks you out.
						(if (and (< 750 Clock) (< Clock 2550))
							(FixTime 21)
						)
						(curRoom newRoom: 37)
					)
				)
			)
		)
	)
)

(instance lGuard of Prop
	(properties
		y 119
		x 120
		view vCastleGuards2
		loop 1
	)
	
	(method (doit)
		(if (or (not (Btst SAVED_BARNARD)) (Btst OBTAINED_BARNARD_REWARD))
			(if
				(and
					(not local8)
					(< (ego y?) 142)
					(< 99 (ego x?))
					(< (ego x?) 209)
				)
				(= local8 1)
				(= frederickSays 12)
				(lGuard setScript: lGuardTalks)
			)
			(if
				(or
					(< (ego x?) 78)
					(< 228 (ego x?))
					(> (ego y?) 155)
				)
				(= local8 0)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if (MouseClaimed lGuard event shiftDown)
					(HighPrint 41 13)
					;Plays a mean horn.  A member in good standing of the musicians guild.
				)
			)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'climb[/castle,wall]')
						(HighPrint 41 14)
						;You don't think the guards would allow you to do so.
						)
					((Said 'fight,kill/guard,man,gatekeeper')
						(EgoDead 41 15
							#title {Getting into the castle the hard way.}
							#icon vEgoJailDeath 3 0
							;No sooner do you draw your weapon then the guards have their weapons out and quickly disarm you.
							;It seems that they practice with the Baron's Weapons Master.
							;They promptly drag you into the castle and down into the dungeon,
							;which is not a very likely spot for the attainment of hero status.
						)
					)
					((Said 'look/horn')
						(HighPrint 41 16)
						;The horns are tarnished and starting to corrode.
						)
					((Said 'look/guard')
						(HighPrint 41 17)
						;The guards look to be middle-aged, but in good physical condition.
						;Their clothing is faded and patched.  Even the horns look dull.
						)
					((Said 'chat[<to]/guard,man,gatekeeper')
						(HighPrint 41 18)
						;Go ahead.
						)
					((Said 'chat,ask//moustache')
						(= pierreSays 11)
						(self setScript: rGuardTalks)
						(= local2 0)
						(= local6 0)
					)
					((Said 'open/door') (= frederickSays 12) (self setScript: lGuardTalks))
					(local2
						(cond 
							(local6
								(event claimed: TRUE)
								(= local6 0)
								(= frederickSays 11)
								(self setScript: lGuardTalks)
							)
							((Said 'chat,ask>') (event claimed: TRUE)
								(HighPrint 41 19)
								;The guards are ignoring you.
								)
							(else (event claimed: FALSE))
						)
					)
					((Said '/hey') (= frederickSays 2) (self setScript: lGuardTalks))
					((Said 'call/guard,man,gatekeeper')
						(if local5
							(= frederickSays 3)
							(self setScript: lGuardTalks)
						else
							(= pierreSays 2)
							(self setScript: rGuardTalks)
						)
					)
					((Said 'say') (= frederickSays 4) (self setScript: lGuardTalks))
					((Said 'chat,ask>')
						(cond 
							((Said '//consent') (= pierreSays 3) (self setScript: rGuardTalks))
							((Said '//baron,baron,hamlet') (= pierreSays 4) (self setScript: rGuardTalks))
							((Said '//castle') (= frederickSays 5) (self setScript: lGuardTalks))
							((Said '//barnard,barnard,barnard') (= pierreSays 5) (self setScript: rGuardTalks))
							((Said '//daughter,elsa') (= frederickSays 6) (self setScript: lGuardTalks))
							((Said '//stable') (= pierreSays 6) (self setScript: rGuardTalks))
							((or (Said '//guard') (Said '//name,handle')) (= frederickSays 7) (self setScript: lGuardTalks))
							((Said '//barrack') (= frederickSays 8) (self setScript: lGuardTalks))
							((Said '//place[<bald]')
								(= frederickSays 9)
								(self setScript: lGuardTalks)
								(= local2 300)
								(= local6 1)
							)
							((Said '//place') (= pierreSays 9) (self setScript: rGuardTalks))
							((Said '//karl') (= frederickSays 13) (self setScript: lGuardTalks))
							(else
								(event claimed: 1)
								(if local5
									(= frederickSays 10)
									(self setScript: lGuardTalks)
								else
									(= pierreSays 8)
									(self setScript: rGuardTalks)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance rGuard of Prop
	(properties
		y 119
		x 197
		view vCastleGuards2
	)
	
	(method (doit)
		(if (or (not (Btst SAVED_BARNARD)) (Btst OBTAINED_BARNARD_REWARD))
			(if
				(and
					(not local7)
					(< (ego y?) 126)
					(< 99 (ego x?))
					(< (ego x?) 209)
				)
				(= local7 1)
				(= pierreSays 13)
				(rGuard setScript: rGuardTalks)
			)
			(if
				(or
					(< (ego x?) 78)
					(< 228 (ego x?))
					(> (ego y?) 140)
				)
				(= local7 0)
			)
			(if local2 (-- local2))
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if (MouseClaimed rGuard event shiftDown)
					(HighPrint 41 20)
					;He's very proud of his moustache, but don't mention his bald spot.
				)
			)
		)
	)
)

(instance rGuardHead of Prop
	(properties
		y 86
		x 204
		view vCastleGuards2
		loop 2
		cycleSpeed 2
	)
)

(instance lGuardHead of Prop
	(properties
		y 85
		x 112
		view vCastleGuards2
		loop 5
		cycleSpeed 2
	)
)

(instance door1 of Prop
	(properties
		y 117
		x 138
		view vCastleGuards2
		loop 3
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if
					(or
						(and local0 (MouseClaimed door1 event shiftDown))
						(and local0 (MouseClaimed door2 event shiftDown))
						(and
							(MouseClaimed door1 event shiftDown)
							(not (MouseClaimed torch event shiftDown))
						)
						(and
							(MouseClaimed door2 event shiftDown)
							(not (MouseClaimed torch event shiftDown))
						)
					)
					(HighPrint 41 21)
					;Doorway to castle great hall.
				)
			)
		)
	)
)

(instance door2 of Prop
	(properties
		y 117
		x 178
		view vCastleGuards2
		loop 4
	)
)

(instance torch of Prop
	(properties
		y 82
		x 158
		view vCastleGuards2
		loop 6
		cycleSpeed 3
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if
				(and (not local0) (MouseClaimed torch event shiftDown))
					(HighPrint 41 22)
					;The torch lights the way to the Baron's Hall.
				)
			)
		)
	)
)

(instance onTrees1 of RFeature
	(properties
		nsTop 42
		nsLeft 5
		nsBottom 120
		nsRight 83
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed onTrees1 event shiftDown)
					(MouseClaimed onTrees2 event shiftDown)
				)
				(HighPrint 41 23)
			)
		)
	)
)

(instance onTrees2 of RFeature
	(properties
		nsTop 52
		nsLeft 233
		nsBottom 112
		nsRight 307
	)
)

(instance onWeed1 of RFeature
	(properties
		nsTop 136
		nsLeft 82
		nsBottom 143
		nsRight 93
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(not (MouseClaimed ego event shiftDown))
					(or
						(MouseClaimed onWeed1 event shiftDown)
						(MouseClaimed onWeed2 event shiftDown)
						(MouseClaimed onWeed3 event shiftDown)
						(MouseClaimed onWeed4 event shiftDown)
						(MouseClaimed onWeed5 event shiftDown)
					)
				)
				(HighPrint 41 24)
			)
			(else (event claimed: 0))
		)
	)
)

(instance onWeed2 of RFeature
	(properties
		nsTop 174
		nsLeft 132
		nsBottom 180
		nsRight 141
	)
)

(instance onWeed3 of RFeature
	(properties
		nsTop 137
		nsLeft 220
		nsBottom 142
		nsRight 227
	)
)

(instance onWeed4 of RFeature
	(properties
		nsTop 176
		nsLeft 274
		nsBottom 187
		nsRight 287
	)
)

(instance onWeed5 of RFeature
	(properties
		nsTop 182
		nsLeft 53
		nsBottom 188
		nsRight 64
	)
)

(instance onCastle of RFeature
	(properties
		nsBottom 41
		nsRight MAXRIGHT
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onCastle event shiftDown) (HighPrint 41 25))
		)
	)
)

(instance onStones of RFeature
	(properties
		nsTop 142
		nsLeft 65
		nsBottom 189
		nsRight 278
	)

	;CI: NOTE: This is a manual decompilation from asm code.
	(method (handleEvent event)
		(cond
			((super handleEvent: event))
			;if the mouse is on the stones, but not on ego nor any of the weeds, 
			;then show a funny message about the stone's origin.
			((and (MouseClaimed onStones event shiftDown)
				(not (or (MouseClaimed ego event shiftDown) 
					(MouseClaimed onWeed1 event shiftDown)
					(MouseClaimed onWeed2 event shiftDown)
					(MouseClaimed onWeed3 event shiftDown)
					(MouseClaimed onWeed4 event shiftDown)
					(MouseClaimed onWeed5 event shiftDown)
				)))
				(cond
					((== stoneLookCount 0)
						(HighPrint 41 26)
						;These flagstones are from a rock quarry in eastern Germany.
						(= stoneLookCount (+ stoneLookCount 1))
					)
					((== stoneLookCount 1)
						(HighPrint 41 27)
						;Well...maybe the flagstones are from western Germany.
						(= stoneLookCount (+ stoneLookCount 1))
					)
					((== stoneLookCount 2)
						(HighPrint 41 28)
						;Flagstones from Europe?
						(= stoneLookCount (+ stoneLookCount 1))
					)
					((== stoneLookCount 3)
						(HighPrint 41 29)
						;Granite from our very own mountains?
						(= stoneLookCount (+ stoneLookCount 1))
					)
					(else
						(HighPrint 41 30)
						;I don't know!
						(= stoneLookCount 0)
					)
				)
			)
			(else
				(event claimed: FALSE)
			)
		)
	)
	
;;; 	(method (handleEvent event)
;;; 		(asm
;;; 			pushi    #handleEvent
;;; 			pushi    1
;;; 			lsp      event
;;; 			super    RFeature,  6
;;; 			bnt      code_0eca
;;; 			jmp      code_0fa3
;;; code_0eca:
;;; 			pushi    3
;;; 			lofsa    onStones
;;; 			push    
;;; 			lsp      event
;;; 			pushi    3
;;; 			callb    MouseClaimed,  6
;;; 			bnt      code_0f9b
;;; 			pushi    3
;;; 			lsg      ego
;;; 			lsp      event
;;; 			pushi    3
;;; 			callb    MouseClaimed,  6
;;; 			bt       code_0f35
;;; 			pushi    3
;;; 			lofsa    onWeed1
;;; 			push    
;;; 			lsp      event
;;; 			pushi    3
;;; 			callb    MouseClaimed,  6
;;; 			bt       code_0f35
;;; 			pushi    3
;;; 			lofsa    onWeed2
;;; 			push    
;;; 			lsp      event
;;; 			pushi    3
;;; 			callb    MouseClaimed,  6
;;; 			bt       code_0f35
;;; 			pushi    3
;;; 			lofsa    onWeed3
;;; 			push    
;;; 			lsp      event
;;; 			pushi    3
;;; 			callb    MouseClaimed,  6
;;; 			bt       code_0f35
;;; 			pushi    3
;;; 			lofsa    onWeed4
;;; 			push    
;;; 			lsp      event
;;; 			pushi    3
;;; 			callb    MouseClaimed,  6
;;; 			bt       code_0f35
;;; 			pushi    3
;;; 			lofsa    onWeed5
;;; 			push    
;;; 			lsp      event
;;; 			pushi    3
;;; 			callb    MouseClaimed,  6
;;; code_0f35:
;;; 			not     
;;; 			bnt      code_0f9b
;;; 			lsl      stoneLookCount
;;; 			dup     
;;; 			ldi      0
;;; 			eq?     
;;; 			bnt      code_0f4f
;;; 			pushi    2
;;; 			pushi    41
;;; 			pushi    26
;;; 			callb    HighPrint,  4
;;; 			+al      stoneLookCount
;;; 			jmp      code_0f97
;;; code_0f4f:
;;; 			dup     
;;; 			ldi      1
;;; 			eq?     
;;; 			bnt      code_0f63
;;; 			pushi    2
;;; 			pushi    41
;;; 			pushi    27
;;; 			callb    HighPrint,  4
;;; 			+al      stoneLookCount
;;; 			jmp      code_0f97
;;; code_0f63:
;;; 			dup     
;;; 			ldi      2
;;; 			eq?     
;;; 			bnt      code_0f77
;;; 			pushi    2
;;; 			pushi    41
;;; 			pushi    28
;;; 			callb    HighPrint,  4
;;; 			+al      stoneLookCount
;;; 			jmp      code_0f97
;;; code_0f77:
;;; 			dup     
;;; 			ldi      3
;;; 			eq?     
;;; 			bnt      code_0f8b
;;; 			pushi    2
;;; 			pushi    41
;;; 			pushi    29
;;; 			callb    HighPrint,  4
;;; 			+al      stoneLookCount
;;; 			jmp      code_0f97
;;; code_0f8b:
;;; 			pushi    2
;;; 			pushi    41
;;; 			pushi    30
;;; 			callb    HighPrint,  4
;;; 			ldi      0
;;; 			sal      stoneLookCount
;;; code_0f97:
;;; 			toss    
;;; 			jmp      code_0fa3
;;; code_0f9b:
;;; 			pushi    #claimed
;;; 			pushi    1
;;; 			pushi    0
;;; 			lap      event
;;; 			send     6
;;; code_0fa3:
;;; 			ret     
;;; 		)
;;; 	)
)

(instance lGuardTrumpets of Script
	(properties)
	
	(method (doit)
		(if (== (cSound prevSignal?) 10) (self cue:))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(lGuard
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
		)
	)
)

(instance rGuardTrumpets of Script
	(properties)
	
	(method (doit)
		(if (== (cSound prevSignal?) 20) (self changeState: 7))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(cSound number: 96 loop: -1 priority: 5 play:)
				(rGuard
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2 (= seconds 1))
			(3
				(TimePrint 6 41 31)
				;The Baron von Spielburg and Baronet await you in the great hall.
				(= cycles 30)
			)
			(4
				(torch init: setCycle: Forward)
				(door1 setCycle: EndLoop self)
				(door2 setCycle: EndLoop)
			)
			(5
				(door1 stopUpd:)
				(door2 stopUpd:)
				(= seconds 2)
			)
			(6
				(ego illegalBits: 0 setMotion: MoveTo 158 100)
			)
			(7 (curRoom newRoom: 141))
		)
	)
)

(instance lGuardTalks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(lGuardHead setPri: 8 init: setCycle: Forward)
				(= cycles 25)
			)
			(1
				(= local5 0)
				(HandsOn)
				(switch frederickSays
					(1
						(HighPrint 41 32)
						;"No one enters the castle without the Baron's permission."
						)
					(2
						(HighPrint 41 33)
						;"Hay is for horses."
						)
					(3
						(HighPrint 41 34)
						;"I'm here!  What do you want?"
						)
					(4
						(HighPrint 41 35)
						;"Says you."
						)
					(5
						(HighPrint 41 36)
						;"This is the castle of the Baron Stefan von Spielburg."
						)
					(6
						(HighPrint 41 37)
						;"The Baron's daughter has been gone for years."
						)
					(7
						(HighPrint 41 38)
						;"I'm Frederick."
						(= pierreSays 7)
						(self setScript: rGuardTalks)
					)
					(8
						(HighPrint 41 39)
						;"The barracks are off to my right, but you have no business going there."
						)
					(9
						(HighPrint 41 40)
						;I told you not to mention it.  Now you've hurt his feelings, and he won't talk until he gets over it.
						)
					(10
						(HighPrint 41 41)
						;"I can't help you on that."
						(= pierreSays 10)
						(self setScript: rGuardTalks)
					)
					(11
						(HighPrint 41 42)
						;"Looks like meathead is sulking.  If I talk to you anymore he'll get even with me."
						(= pierreSays 12)
						(self setScript: rGuardTalks)
					)
					(12
						(HighPrint 41 32)
						;"No one enters the castle without the Baron's permission."
						)
					(13
						(HighPrint 41 43)
						;"Karl is the gatekeeper.  He is a good one to answer questions."
						)
				)
				(lGuardHead dispose:)
			)
		)
	)
)

(instance rGuardTalks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(rGuardHead setPri: 8 init: setCycle: Forward)
				(= cycles 25)
			)
			(1
				(= local5 1)
				(HandsOn)
				(switch pierreSays
					(1
						(HighPrint 41 44)
						;"The Baron sees no one."
						)
					(2
						(HighPrint 41 45)
						;"We cannot leave our post."
						)
					(3
						(HighPrint 41 46)
						;"You will have to see the Baron to get his permission."
						)
					(4
						(if (Btst SAVED_BARNARD)
							(HighPrint 41 47)
							;The Baron is busy and will see no one.
						else
							(HighPrint 41 44)
							;"The Baron sees no one."
						)
					)
					(5
						(if (Btst SAVED_BARNARD)
							(HighPrint 41 48)
							;The Baronet is in the castle recovering from his ordeal and will see no one.
						else
							(HighPrint 41 49)
							;"The Baronet Barnard von Spielburg is missing."
						)
					)
					(6
						(HighPrint 41 50)
						;"The stable is off to my left."
						)
					(7
						(HighPrint 41 51)
						;"My name is Pierre.  I'm one of the Baron's personal guards."
						(= local5 0)
					)
					(8
						(HighPrint 41 52)
						;"It is not our place to answer questions."
						)
					(9
						(HighPrint 41 53)
						;"Spot?  What spot?"
						)
					(10
						(HighPrint 41 54)
						;"Why don't you ask Karl?"
						(= local5 0)
					)
					(11
						(HighPrint 41 55)
						;"Why thank you.  Your manners have improved."
						)
					(12
						(HighPrint 41 56)
						;"Don't get me mad."
						(= local5 0)
					)
					(13
						(HighPrint 41 44)
						;"The Baron sees no one."
						)
				)
				(rGuardHead dispose:)
			)
		)
	)
)

(instance leaveHall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					posn: 159 120
					setMotion: MoveTo 159 150 self
				)
				(= curRoomNum 141)
				(FixTime 22)
				(EgoSleeps 7 30)
				(= curRoomNum 41)
			)
			(1
				(ego ignoreActors: 0 illegalBits: cWHITE)
				(TimePrint 10 41 57)
				;After a wonderful meal with the Baron and his son, a peaceful night's sleep in a featherdown bed,
				;and a filling breakfast in bed, you are ready to go adventuring once more.
				(HandsOn)
				(Bset OBTAINED_BARNARD_REWARD)
				(= enteringCastle FALSE)
				(client setScript: 0)
			)
		)
	)
)
