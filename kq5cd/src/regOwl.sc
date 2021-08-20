;;; Sierra Script 1.0 - (do not remove this comment)
(script# 202)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	owl 0
	stdWalkIn 1
	stdWalkOut 2
	rotate 3
	cedric 4
)

(local
	local0 = [1000 116 62 4 11 24 19 23 30]
	clientView
	clientLoop
	clientCel
	local13
	local14 = [2 6 7 8 10 11 12 27 29 30 31 35 44 45 46 52 53 54 -1]
	local33 = [0 2 4 6 100 100 8 9 11 100 100 12 100 100 13 100 100 100 -1]
	local52 = [57 58 59 60 61 62 63 64 65 66 66 67 68 69 70 71 72 73 74]
	local71 = [3005 3006 3007 3008 3009 3010 3011 3012 3013 3014 3015 3016 3017 3018]
	local85
)
(procedure (localproc_001e)
	(= [local0 1] (Min (Max 0 (- (cedric x?) 40)) 239))
	(= [local0 2] (Min (Max 0 (- (cedric y?) 40)) 239))
)

(instance owl of Region
	
	(method (init)
		(super init:)
		(= clientView 138)
		(if
			(or
				(== curRoomNum 29)
				(== curRoomNum 30)
				(== curRoomNum 27)
				(== curRoomNum 1)
				(== curRoomNum 52)
			)
			(= clientView 140)
			(cedric view: 136)
		else
			(cedric view: 137)
		)
		(cedric
			ignoreHorizon: TRUE
			setPri: 14
			loop: 8
			cel: 0
			setCycle: (if (== (theGame detailLevel:) 3) Walk else 0)
			ignoreActors: TRUE
			posn: cedricX cedricY
			init:
			stopUpd:
		)
		(if
			(or
				(== curRoomNum 9)
				(== curRoomNum 32)
				(== curRoomNum 30)
			)
			(cedric setPri: -1)
		)
		(if
			(or
				(== ((inventory at: iStick) owner?) 12)
				(not (ego has: iStick))
			)
			(= [local71 8] 3048)
		)
		(= globalCedric cedric)
		(self setScript: messageTime)
	)
	
	(method (doit)
		(cond 
			((== globalCedric 50)
				(= globalCedric cedric)
				(= [local71 8] 3048)
			)
			(script
				(script doit:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance messageTime of Script

	(method (doit)
		(super doit:)
		(if (or (curRoom script?) (== (User controls?) 0))
			(= state -1)
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 120 240))
			)
			(1
				(if globalCedric
					(localproc_001e)
					(proc762_1 @local0 global325 self)
				)
			)
			(2 (self dispose:))
		)
	)
)

(class regOwl of Actor
	(properties
		speak 0
	)
)

(instance cedric of regOwl
	(properties
		view 138
		illegalBits $0000
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bt       code_044d
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      16384
			eq?     
			not     
			bt       code_044d
			pushi    2
			pushSelf
			lsp      event
			calle    MousedOn,  4
			not     
			bnt      code_0451
code_044d:
			ret     
			jmp      code_05d0
code_0451:
			ldi      0
			sat      temp1
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      2
			eq?     
			bnt      code_04a8
			ldi      0
			sat      temp0
code_0468:
			lst      temp0
			ldi      17
			le?     
			bnt      code_048f
			lsg      curRoomNum
			lat      temp0
			lali     local14
			eq?     
			bnt      code_048a
			ldi      1
			sat      temp1
			pushi    1
			lat      temp0
			lsli     local52
			callb    SpeakAudio,  2
			jmp      code_048f
			ret     
code_048a:
			+at      temp0
			jmp      code_0468
code_048f:
			lat      temp1
			not     
			bnt      code_049d
			pushi    1
			lat      temp0
			lsli     local52
			callb    SpeakAudio,  2
code_049d:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_05cf
code_04a8:
			dup     
			ldi      4
			eq?     
			bnt      code_04f2
			pushi    #indexOf
			pushi    1
			pushi    #curInvIcon
			pushi    0
			lag      theIconBar
			send     4
			push    
			lag      inventory
			send     6
			push    
			dup     
			ldi      28
			eq?     
			bnt      code_04d3
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
			jmp      code_04ee
code_04d3:
			pushi    0
			call     localproc_001e,  0
			pushi    2
			lea      @local0
			push    
			pushi    3019
			calle    proc762_1,  4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
code_04ee:
			toss    
			jmp      code_05cf
code_04f2:
			dup     
			ldi      5
			eq?     
			bnt      code_05cf
			ldi      0
			sat      temp0
code_04fd:
			lst      temp0
			ldi      15
			le?     
			bnt      code_0582
			lsg      curRoomNum
			lat      temp0
			lali     local14
			eq?     
			bnt      code_057d
			lat      temp0
			lali     local33
			sat      temp1
			lst      temp0
			ldi      1
			add     
			lali     local33
			sat      temp2
			lsl      local13
			ldi      1
			eq?     
			bnt      code_0541
			lst      temp2
			ldi      100
			eq?     
			bt       code_053a
			lst      temp2
			lat      temp1
			sub     
			push    
			ldi      1
			eq?     
			bnt      code_0541
code_053a:
			ldi      0
			sat      temp1
			jmp      code_0582
code_0541:
			lst      temp1
			ldi      100
			eq?     
			bt       code_0551
			lsl      local13
			ldi      2
			eq?     
			bnt      code_055b
code_0551:
			ldi      0
			sat      temp1
			jmp      code_0582
			jmp      code_057d
code_055b:
			pushi    0
			call     localproc_001e,  0
			pushi    2
			lea      @local0
			push    
			lsl      local13
			lat      temp1
			add     
			lsli     local71
			calle    proc762_1,  4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			+al      local13
			ret     
code_057d:
			+at      temp0
			jmp      code_04fd
code_0582:
			lat      temp1
			not     
			bnt      code_05c7
			pushi    2
			pushi    0
			pushi    3
			callk    Random,  4
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_05a0
			pushi    1
			pushi    75
			callb    SpeakAudio,  2
			jmp      code_05c6
code_05a0:
			dup     
			ldi      1
			eq?     
			bnt      code_05b0
			pushi    1
			pushi    76
			callb    SpeakAudio,  2
			jmp      code_05c6
code_05b0:
			dup     
			ldi      2
			eq?     
			bnt      code_05c0
			pushi    1
			pushi    77
			callb    SpeakAudio,  2
			jmp      code_05c6
code_05c0:
			pushi    1
			pushi    78
			callb    SpeakAudio,  2
code_05c6:
			toss    
code_05c7:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
code_05cf:
			toss    
code_05d0:
			ret     
		)
	)
	
	(method (delete)
		(if (& signal delObj)
			(if (!= mover -1)
				(self setMotion: 0)
			)
			(self setScript: 0 setCycle: 0)
		)
		(if (& signal delObj)
			(&= signal $7fff)
			(cast delete: self)
			(if underBits
				(UnLoad MEMORY underBits)
				(= underBits 0)
			)
			(if (& signal viewAdded)
				(addToPics
					add:
						((PicView new:)
							view: view
							loop: loop
							cel: cel
							x: x
							y: y
							z: z
							priority: priority
							signal: signal
							yourself:
						)
				)
				(features addToFront: self)
			else
				(DisposeClone self)
			)
			(if (IsObject actions)
				(actions dispose:)
			)
			(= actions 0)
		)
	)
)

(instance stdWalkIn of Script
	(properties)
	
	(method (changeState newState &tmp theEgoX theEgoY egoX egoY egoWide egoHigh temp6 [temp7 2])
		(switch (= state newState)
			(0
				(if (< (theGame detailLevel:) 3)
					(= local85 1)
					(ego edgeHit: 0)
					(HandsOff)
					(= register (curRoom roomToEdge: prevRoomNum))
					(= egoHigh (CelHigh (ego view?) (ego loop?) (ego cel?)))
					(= egoWide (CelWide (ego view?) (ego loop?) (ego cel?)))
					(= egoX (ego x?))
					(= egoY (ego y?))
					(switch register
						(3
							(= theEgoX egoX)
							(= theEgoY (+ 189 egoHigh))
						)
						(2
							(= theEgoY egoY)
							(= theEgoX (+ 319 egoWide))
						)
						(4
							(= theEgoY egoY)
							(= theEgoX (- 0 egoWide))
						)
						(1
							(= theEgoX egoX)
							(= theEgoY (- egoY 2))
						)
					)
					(ego
						posn: theEgoX theEgoY
						init:
						setLoop: -1
						ignoreActors: 1
						setMotion: MoveTo egoX egoY
					)
					(cedric
						view: (if (OneOf curRoomNum 29 30 27 1 52) 136 else 137)
						loop: 8
						cel: 2
						addToPic:
					)
					(= cycles 1)
				else
					(if (OneOf curRoomNum 29 30 27 1 52)
						(cedric view: 140)
					else
						(cedric view: 138)
					)
					(ego edgeHit: 0)
					(HandsOff)
					(= register (curRoom roomToEdge: prevRoomNum))
					(= egoHigh (CelHigh (ego view?) (ego loop?) (ego cel?)))
					(= egoWide (CelWide (ego view?) (ego loop?) (ego cel?)))
					(= egoX (ego x?))
					(= egoY (ego y?))
					(switch register
						(3
							(= theEgoX egoX)
							(= theEgoY (+ 189 egoHigh))
							(= temp6 3)
						)
						(2
							(= theEgoY egoY)
							(= theEgoX (+ 319 egoWide))
							(= temp6 1)
						)
						(4
							(= theEgoY egoY)
							(= theEgoX (- 0 egoWide))
							(= temp6 0)
						)
						(1
							(= theEgoX egoX)
							(= theEgoY (- egoY 2))
							(= temp6 2)
						)
					)
					(ego
						posn: theEgoX theEgoY
						init:
						setLoop: -1
						ignoreActors: 1
						setMotion: MoveTo egoX egoY
					)
					(cedric
						loop: temp6
						cel: 0
						cycleSpeed: 3
						setCycle: EndLoop self
					)
				)
			)
			(1
				(if (not local85) (cedric setScript: rotate))
				(= cycles 1)
			)
			(2
				(if (ego mover?) (-- state))
				(= cycles 1)
			)
			(3
				(HandsOn)
				(ego ignoreActors: 0)
				(client setScript: 0)
			)
		)
	)
)

(instance stdWalkOut of Script
	
	(method (changeState newState &tmp egoX egoY temp2 temp3)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local85
					(= egoX (ego x?))
					(= egoY (ego y?))
					(= temp3 5)
					(switch register
						(1
							(-= egoY 10)
							(= temp2 7)
						)
						(3
							(+= egoY 60)
							(= temp2 0)
						)
						(2
							(+= egoX 20)
							(= temp2 4)
						)
						(4
							(-= egoX 20)
							(= temp2 4)
							(= temp3 4)
						)
					)
					(if (!= register 1)
						(ego ignoreActors: TRUE setMotion: MoveTo egoX egoY self)
					else
						(ego loop: 3)
						(= cycles 1)
					)
				else
					(= cycles 1)
				)
			)
			(1
				(if local85
					(= cycles 1)
				else
					(cedric
						view: clientView
						loop: clientLoop
						cel: clientCel
						setScript: 0
					)
					(= egoX (ego x?))
					(= egoY (ego y?))
					(= temp3 5)
					(switch register
						(1
							(= egoY (- egoY 10))
							(= temp2 7)
						)
						(3
							(= egoY (+ egoY 60))
							(= temp2 0)
						)
						(2
							(= egoX (+ egoX 20))
							(= temp2 4)
						)
						(4
							(= egoX (- egoX 20))
							(= temp2 4)
							(= temp3 4)
						)
					)
					(if (!= temp2 0)
						(cedric
							loop: temp3
							cycleSpeed: 0
							setCycle: CycleTo temp2 1 self
						)
					else
						(= cycles 1)
					)
					(if (!= register 1)
						(ego ignoreActors: 1 setMotion: MoveTo egoX egoY)
					else
						(ego loop: 3)
					)
				)
			)
			(2
				(cond 
					(local85 (= local85 0) (= cycles 1))
					((== register 1)
						(cedric
							cycleSpeed: 1
							loop: 9
							cel: 0
							setCycle: CycleTo 4 1 self
						)
					)
					(else
						(cedric
							cycleSpeed: 1
							loop:
							(switch register
								(2 6)
								(4 7)
								(else  8)
							)
							cel: 0
							setCycle: EndLoop self
						)
					)
				)
			)
			(3
				(HandsOn)
				(curRoom newRoom: (curRoom edgeToRoom: register))
			)
		)
	)
)

(instance rotate of Script
	
	(method (changeState newState &tmp temp0 temp1 temp2 temp3)
		(switch (= state newState)
			(0
				(if (!= (client loop?) 2)
					(client
						cycleSpeed: 0
						cel: (if (< (client loop?) 2) 4 else 7)
						loop: (if (== (client loop?) 0) 5 else 4)
						setCycle: CycleTo 0 -1 self
					)
				else
					(= cycles 1)
				)
			)
			(1
				(if (== (client view?) 138)
					(= temp2 137)
				else
					(= temp2 136)
				)
				(= clientView (client view?))
				(= clientLoop (client loop?))
				(= clientCel (client cel?))
				(client view: temp2 loop: 8 cycleSpeed: 3 cel: 2)
				(= cycles 1)
			)
			(2
				(cond 
					((> (= temp1 (Random 0 4)) (client cel?)) (= temp0 1))
					((< temp1 (client cel?)) (= temp0 -1))
					((== temp1 4) (-- temp1) (= temp0 -1))
					(else (++ temp1) (= temp0 1))
				)
				(client setCycle: CycleTo temp1 temp0)
				(-- state)
				(= seconds (Random 5 10))
			)
			(3 (client setScript: 0))
		)
	)
)
