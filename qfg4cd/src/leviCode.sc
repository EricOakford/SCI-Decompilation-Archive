;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include sci.sh)
(use Main)
(use EgoDead)
(use Array)
(use PolyPath)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	leviCode 0
	sLevitate 1
)

(local
	gTheObj_2X
	gTheObj_2Y
	local2 =  100
	local3
	local4
	local5
	local6
	gTheObj_2Loop
	local8
	local9
	newProp
	local11
	local12
	newProp_2
)
(instance leviCode of Code
	(properties)
	
	(method (init param1 param2 param3 param4 param5)
		(theGame handsOff:)
		(ego setMotion: 0)
		(if argc
			(= gTheObj_2X param1)
			(= gTheObj_2Y param2)
		else
			(= gTheObj_2X (ego x?))
			(= gTheObj_2Y (ego y?))
			(sLevitate start: 2)
		)
		(if (> argc 2) (= local2 param3))
		(if (> argc 3) (= local12 param4) else (= local12 0))
		(if (> argc 4) (= local3 (Min param5 3)))
		((ScriptID curRoomNum) setScript: sLevitate)
	)
)

(instance sLevitate of Script
	(properties)
	
	(method (doit param1 &tmp temp0)
		(asm
			pushi    #doit
			pushi    0
			&rest    param1
			super    Script,  4
			pTos     state
			ldi      12
			lt?     
			bnt      code_0266
			pTos     state
			ldi      5
			gt?     
			bnt      code_01b6
			pushi    #mover
			pushi    0
			lag      ego
			send     4
			bnt      code_013c
			pTos     state
			ldi      8
			eq?     
			bnt      code_00d7
			ldi      0
			sal      local11
			jmp      code_01b6
code_00d7:
			pushi    #isKindOf
			pushi    1
			class    PolyPath
			push    
			pushi    #mover
			pushi    0
			lag      ego
			send     4
			send     6
			bnt      code_010b
			lsl      gTheObj_2Y
			pushi    #finalY
			pushi    0
			pushi    #mover
			pushi    0
			lag      ego
			send     4
			send     4
			sub     
			push    
			pushi    #z
			pushi    0
			lag      ego
			send     4
			add     
			sal      local11
			jmp      code_0127
code_010b:
			lsl      gTheObj_2Y
			pushi    #y
			pushi    0
			pushi    #mover
			pushi    0
			lag      ego
			send     4
			send     4
			sub     
			push    
			pushi    #z
			pushi    0
			lag      ego
			send     4
			add     
			sal      local11
code_0127:
			pushi    #y
			pushi    1
			lsl      gTheObj_2Y
			pushi    1
			pushi    1
			lsl      gTheObj_2X
			pushi    322
			pushi    1
			pushi    0
			lag      ego
			send     18
			jmp      code_01b6
code_013c:
			pTos     state
			ldi      6
			eq?     
			bnt      code_01b6
			pushi    #z
			pushi    0
			lag      ego
			send     4
			push    
			lal      local2
			lt?     
			bnt      code_019a
			lsl      local11
			pushi    #z
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0176
			pushi    3
			pushi    #x
			pushi    #z
			pushi    0
			lag      ego
			send     4
			push    
			ldi      1
			sub     
			push    
			lag      ego
			send     6
			jmp      code_01b6
code_0176:
			lsl      local11
			pushi    #z
			pushi    0
			lag      ego
			send     4
			gt?     
			bnt      code_01b6
			pushi    3
			pushi    #x
			pushi    #z
			pushi    0
			lag      ego
			send     4
			push    
			ldi      1
			add     
			push    
			lag      ego
			send     6
			jmp      code_01b6
code_019a:
			lsl      local11
			lal      local2
			lt?     
			bnt      code_01b6
			pushi    3
			pushi    #x
			pushi    #z
			pushi    0
			lag      ego
			send     4
			push    
			ldi      1
			sub     
			push    
			lag      ego
			send     6
code_01b6:
			lal      newProp
			bnt      code_01cb
			pushi    #z
			pushi    1
			pushi    #z
			pushi    0
			lag      ego
			send     4
			push    
			lal      newProp
			send     6
code_01cb:
			lal      newProp_2
			bnt      code_01f4
			pushi    4
			pushi    1
			pushi    #textLeft
			pushi    #z
			pushi    0
			lag      ego
			send     4
			sub     
			push    
			pushi    5
			pushi    1
			pushi    128
			pushi    #z
			pushi    0
			lag      ego
			send     4
			sub     
			push    
			lal      newProp_2
			send     12
code_01f4:
			pTos     state
			ldi      6
			eq?     
			bnt      code_0266
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      17
			gt?     
			bnt      code_0218
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      21
			lt?     
			bnt      code_0218
code_0218:
			not     
			bnt      code_0244
			pushi    1
			lsg      gameTime
			lal      local9
			sub     
			push    
			callk    Abs,  2
			push    
			ldi      50
			gt?     
			bnt      code_0244
			lag      gameTime
			sal      local9
			pushi    #useMana
			pushi    1
			pushi    1
			lag      ego
			send     6
			not     
			bnt      code_0244
			pushi    #cue
			pushi    0
			self     4
code_0244:
			pushi    #z
			pushi    0
			lag      ego
			send     4
			push    
			ldi      0
			le?     
			bnt      code_0266
			ldi      10
			aTop     state
			pushi    #z
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    #cue
			pushi    0
			self     4
code_0266:
			pTos     state
			ldi      8
			eq?     
			bnt      code_0299
			pushi    #z
			pushi    0
			lag      ego
			send     4
			push    
			ldi      0
			le?     
			bnt      code_0284
			pushi    #cue
			pushi    0
			self     4
			jmp      code_0299
code_0284:
			pushi    3
			pushi    #x
			pushi    #z
			pushi    0
			lag      ego
			send     4
			push    
			ldi      6
			sub     
			push    
			lag      ego
			send     6
code_0299:
			pushi    #z
			pushi    0
			lag      ego
			send     4
			push    
			ldi      0
			le?     
			bnt      code_02b0
			pushi    #z
			pushi    1
			pushi    0
			lag      ego
			send     6
code_02b0:
			ret     
		)
	)
	
	(method (dispose)
		(= start 0)
		(if newProp (newProp dispose:) (= newProp 0))
		(if newProp_2 (newProp_2 dispose:) (= newProp_2 0))
		(if local4 (local4 dispose:) (= local4 0))
		(if local5 (local5 dispose:) (= local5 0))
		(= useObstacles 1)
		(ego z: 0)
		(= local6 0)
		(super dispose:)
		(if local12 (local12 cue:))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
					(and
						(> (ego view?) 17)
						(< (ego view?) 21)
						(< (ego view?) 21)
					)
					(= local6 1)
				)
				(ego setMotion: PolyPath gTheObj_2X gTheObj_2Y self)
			)
			(1
				(= local4 (IntArray with: 135 225 45 315))
				(= local5 (IntArray with: 0 1 0 2 0 1 2 3))
				(cond 
					(local6
						((ego looper?) dispose:)
						(ego looper: 0)
						(Face ego (+ (ego x?) 5) (+ (ego y?) 5) self)
					)
					(local3 (ego setHeading: (local4 at: local3) self))
					(else (self cue:))
				)
			)
			(2
				(if (not local4)
					(= local4 (IntArray with: 135 225 45 315))
					(= local5 (IntArray with: 0 1 0 2 0 1 2 3))
				)
				(= useObstacles 0)
				(= gTheObj_2Loop (ego loop?))
				(if local6
					(ego
						view: 19
						setLoop: (if (mod gTheObj_2Loop 2) 13 else 12) 1
						setCel: 0
						setCycle: End self
						xStep: 0
						setPri: (ego priority?)
					)
				else
					(ego
						view: 17
						setLoop: (if local3 else (local5 at: (ego loop?))) 1
						setCel: 0
						setCycle: End self
						xStep: 0
						setPri: (ego priority?)
					)
				)
				(soundFX number: 941 play:)
			)
			(3
				(if local6
					(ego
						setLoop: (if (mod gTheObj_2Loop 2) 15 else 14) 1
						setCel: 0
					)
				else
					(ego setCel: 255)
				)
				(ego z: 4)
				((= newProp (Prop new:))
					signal: 26625
					view: 17
					loop: 4
					x: (ego x?)
					y: (+ (ego y?) 1)
					setCycle: Fwd
					setScale:
					scaleX: (ego scaleX?)
					scaleY: (ego scaleY?)
					priority: (ego priority?)
					fixPriority: 1
					init:
				)
				((= newProp_2 (Prop new:))
					signal: 24577
					view: 17
					loop: (if local6 (- (ego loop?) 8) else (+ (ego loop?) 6))
					x: (ego x?)
					y: (+ (ego y?) 1)
					setCycle: Fwd
					setScale: ego
					priority: (ego priority?)
					fixPriority: 1
					init:
				)
				(= ticks 10)
			)
			(4 (ego z: 8) (= ticks 10))
			(5
				(ego z: 12)
				(= local11 12)
				(= ticks 10)
			)
			(6
				(User canControl: 1 canInput: 1)
				(theIconBar enable: 0 advanceCurIcon:)
			)
			(7
				(theGame handsOff:)
				(= local6 0)
				(newProp dispose:)
				(= newProp 0)
				(messager say: 1 6 1 1 self 31)
			)
			(8
				(= register (ego z?))
				(ego setLoop: 10 1)
			)
			(9
				(ShakeScreen 2)
				(newProp_2 dispose:)
				(= newProp_2 0)
				(ego
					view: 6
					setLoop: (if (mod (ego loop?) 2) 2 else 0)
					setCel: 3
					setCycle: End self
				)
				(soundFX number: 909 play:)
			)
			(10
				(if (ego takeDamage: (/ register 5))
					(ego loop: (+ (ego loop?) 2) cel: 0 setCycle: End self)
				else
					(EgoDead 2 31)
				)
			)
			(11
				(if newProp
					(newProp dispose:)
					(newProp_2 dispose:)
					(= newProp_2 0)
					(= newProp 0)
					(if local6
						(ego loop: 5 cel: 4 setCycle: Beg self)
					else
						(ego setCycle: Beg self)
					)
				else
					(ego normalize: gTheObj_2Loop)
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(12
				(if local6
					(ego view: 20 loop: 2 cel: 4)
				else
					(ego normalize: gTheObj_2Loop)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soundFX of Sound
	(properties)
)
