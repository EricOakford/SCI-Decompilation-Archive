;;; Sierra Script 1.0 - (do not remove this comment)
(script# 49)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Path)
(use Motion)
(use Actor)
(use System)

(public
	rm049 0
)

(local
	[local0 3] = [21 43 -32768]
	[local3 5] = [50 56 73 72 -32768]
	[local8 5] = [94 98 114 119 -32768]
	[local13 5] = [120 122 135 122 -32768]
	[local18 9] = [157 111 185 100 238 67 347 20 -32768]
	[local27 11] = [17 63 62 77 106 97 139 107 160 115 -32768]
	[local38 9] = [164 115 185 104 207 90 227 73 -32768]
	[local47 11] = [75 62 137 75 224 76 294 65 332 53 -32768]
	local58
	[local59 10] = [150 129 172 131 151 149 126 153 114 145]
	[local69 32] = [0 0 225 0 225 91 166 107 114 89 110 110 70 112 62 124 167 124 163 139 161 157 191 151 211 108 319 95 319 189 0 189]
	[local101 20] = [0 0 225 0 225 91 166 107 113 89 109 108 70 112 48 132 21 144 0 156]
	[local121 14] = [86 189 171 176 142 158 179 158 211 108 319 95 319 189]
	local135
	[local136 9] = [1000 145 70 4 11 24 19 23 30]
	[local145 9] = [1003 105 71 4 11 25 23 31 31]
)
(instance rm049 of KQ5Room
	(properties
		picture 49
		east 50
		south 48
		west 48
	)
	
	(method (init)
		(Load rsVIEW 654)
		(super init:)
		(self setFeatures: arch beach)
		(ego init: view: 6 illegalBits: -32768)
		(switch prevRoomNum
			(east
				(LoadMany 128 667 22 23 21)
				(if (Btst 55) (theMusic number: 814 loop: -1 playBed:))
				((ego head?) hide:)
				(ego
					posn: 215 100
					setStep: 1 1
					view: (if (Btst 74) 661 else 6)
				)
				(theBoat init: x: 143 y: 140 ignoreActors: 1 setPri: 10)
				(sail
					init:
					setLoop: 2
					setCel: 0
					setPri: 11
					x: (theBoat x?)
					y: (theBoat y?)
				)
				(if (and (not (Btst 74)) (not (Btst 55)))
					(cedric
						posn: (+ (theBoat x?) 16) (theBoat y?) 8
						view: 650
						setLoop: 2
						setCel: 6
						init:
					)
				)
			)
			(else 
				(HandsOff)
				(= global103 0)
				(self setScript: sailInScript)
			)
		)
		(if (Btst 54) (harpy1 init: setScript: harpyInitScript))
		(if (not (ego has: 23))
			(shell init: stopUpd:)
			(glint
				init:
				posn: (shell x?) (shell y?) 0
				setScript: glintScript
			)
		)
		(if (== (theGame detailLevel:) 3)
			(wave0 setCycle: Fwd init:)
			(wave1 setCycle: Fwd init:)
			(wave2 setCycle: Fwd init:)
		)
		(if (== (ego view?) 661)
			(poly2 points: @local69 size: 16)
			(self addObstacle: poly2)
		else
			(poly1 points: @local59 size: 5)
			(poly3 points: @local101 size: 10)
			(poly4 points: @local121 size: 7)
			(self addObstacle: poly1 poly3 poly4)
		)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			((& (= temp1 (ego onControl: 0)) $4000) (curRoom newRoom: 50))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(curRoom newRoom: temp0)
			)
		)
		(cond 
			(local135 0)
			(
				(and
					(& (= temp1 (ego onControl: 0)) $0400)
					(!= (ego view?) 21)
				)
				(ego view: 21)
			)
			((and (& temp1 $0040) (!= (ego view?) 23)) (ego view: 23))
			((and (& temp1 $2000) (!= (ego view?) 22))
				(cond 
					((!= (ego view?) 661) (ego view: 22))
					(local58 0)
					(else (if (not script) (proc0_29 536)) (= local58 1))
				)
			)
			(
				(and
					(!= (ego view?) 6)
					(!= (ego view?) 661)
					(not (& temp1 $2440))
				)
				(ego view: 6)
			)
			(else (= local58 0))
		)
	)
	
	(method (dispose)
		(DisposeScript 983)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance sailInScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (theBoat mover?) ((theBoat mover?) doit:))
		(sail x: (theBoat x?) y: (theBoat y?))
		(wake x: (+ (theBoat x?) 20) y: (- (theBoat y?) 7))
		(ego x: (- (theBoat x?) 8) y: (+ (theBoat y?) 3))
		(cedric x: (+ (theBoat x?) 16) y: (theBoat y?))
	)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(= local135 1)
				(self doit:)
				(theBoat init: setMotion: MoveTo 143 140 self)
				(sail init: setCel: 0)
				(wake init: setCycle: Fwd cycleSpeed: 1 setLoop: 0)
				(if (IsObject (ego head?)) ((ego head?) dispose:))
				(ego normal: 0 view: 654 setLoop: 1 z: 0 setCel: 0)
				(if (not (Btst 55))
					(if (Btst 54)
						(= temp0 650)
						(= temp1 2)
						(= temp2 6)
					else
						(= temp0 654)
						(= temp1 2)
						(= temp2 0)
					)
					(cedric
						init:
						view: temp0
						setLoop: temp1
						setCel: temp2
						z: 8
					)
				)
			)
			(1
				(sail setCycle: End)
				(theBoat stopUpd:)
				(wake dispose:)
				(if (not (Btst 54))
					(curRoom setScript: captureScript)
				else
					(curRoom setScript: getOffBoatScript)
				)
			)
		)
	)
)

(instance getOffBoatScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany 128 22 23 21)
				(ego
					init:
					view: 23
					setLoop: -1
					setCycle: KQ5SyncWalk
					setStep: 1 1
					normal: 1
					posn: (ego x?) (- (ego y?) 5)
					setMotion: MoveTo (- (theBoat x?) 13) (- (theBoat y?) 1) self
				)
			)
			(1
				(= local135 0)
				(ego setPri: -1)
				(HandsOn)
				(theBoat stopUpd:)
				(= global103 0)
				(self dispose:)
			)
		)
	)
)

(instance captureScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 815 loop: -1 playBed: 90)
				(harpy3 init: setScript: harpy3Script)
				(harpy1 init: setCycle: Fwd setMotion: getEgo1 self)
			)
			(1
				(proc762_1 @local136 3074)
				(harpy1 setMotion: getEgo2 self)
			)
			(2
				(proc762_1 @local145 7028)
				(harpy1
					setPri: (+ (sail priority?) 1)
					setMotion: getEgo3 self
				)
				(harpy2 init: setScript: harpy2Script)
			)
			(3
				(harpy1 setMotion: getEgo4 self)
				(ego setPri: 11 setCycle: End self)
			)
			(4 0)
			(5
				(ego z: 0 dispose:)
				(harpy1 setPri: 11 setLoop: 3 setMotion: takeEgo self)
			)
			(6 (curRoom newRoom: 90))
		)
	)
)

(instance harpy2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(harpy2 setCycle: Fwd setMotion: getCed self)
			)
			(1
				(proc0_29 3075 0 1)
				(harpy2 setLoop: 4 setCel: 0 setCycle: End self)
				(cedric dispose:)
			)
			(2
				(harpy2 setLoop: 5 setCycle: Fwd setMotion: takeCed)
			)
		)
	)
)

(instance harpy3Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 25))
			(1
				(harpy3 setCycle: Walk setMotion: guard)
			)
		)
	)
)

(instance harpyInitScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) $0002)
			(HandsOff)
			(curRoom setScript: harpyScript)
			(self dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (Random 1 100) 50) 0 else (self dispose:))
			)
		)
	)
)

(instance harpyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 815 loop: -1 playBed: 90)
				(harpy1
					init:
					setStep: 5 5
					setPri: 11
					setCycle: Fwd
					setMotion: MoveTo (ego x?) (- (ego y?) 26) self
				)
			)
			(1
				(if (IsObject (ego head?)) ((ego head?) dispose:))
				(ego z: 0 dispose:)
				(harpy1 setLoop: 3 setMotion: MoveTo 350 20 self)
			)
			(2
				(= global103 0)
				(= deathMessage 537)
				(EgoDead 247)
			)
		)
	)
)

(instance castOffScript of Script
	(properties)
	
	(method (doit)
		(if (== state 12)
			(if (theBoat mover?) ((theBoat mover?) doit:))
			(sail x: (theBoat x?) y: (theBoat y?))
			(wake x: (- (theBoat x?) 14) y: (+ (theBoat y?) 10))
			(ego x: (+ (theBoat x?) 6) y: (+ (theBoat y?) 1))
			(cedric x: (- (theBoat x?) 9) y: (+ (theBoat y?) 5))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (IsObject (ego head?)) ((ego head?) hide:))
				(ego
					setPri: (- (theBoat priority?) 1)
					setMotion: PolyPath 150 129 self
				)
			)
			(1
				(if (and (not (Btst 74)) (not (Btst 55)))
					(self setScript: grabCedScript self)
					(= register 1)
				else
					(= cycles 1)
				)
			)
			(2
				(= local135 1)
				(if register
					(self setScript: depositCedScript self)
				else
					(= cycles 1)
				)
			)
			(3
				(ego
					setMotion: MoveTo (+ (theBoat x?) 7) (- (theBoat y?) 2) self
				)
			)
			(4
				(ego
					normal: 0
					view: 650
					setLoop: 2
					setCel: 4
					setCycle: 0
					setPri: 10
					posn: (+ (theBoat x?) 7) (theBoat y?) 2
				)
				(= cycles 1)
			)
			(5
				(sail setPri: 11 loop: 8)
				(= cycles 5)
			)
			(6 (sail loop: 0) (= cycles 5))
			(7 (sail loop: 4) (= cycles 5))
			(8 (sail loop: 6) (= cycles 5))
			(9
				(sail loop: 10)
				(= cycles 5)
			)
			(10
				(sail loop: 7)
				(= cycles 5)
			)
			(11
				(sail loop: 5)
				(= cycles 5)
			)
			(12
				(self doit:)
				(theBoat setPri: 10)
				(ego setPri: 11)
				(cedric setPri: 11)
				(sail setPri: 12)
				(wake
					init:
					setCycle: Fwd
					cycleSpeed: 1
					setLoop: 1
					setPri: 11
				)
				(theBoat setMotion: MoveTo -35 182 self)
				(if (IsObject (ego head?)) ((ego head?) dispose:))
			)
			(13
				(ego setPri: -1)
				(Bset 54)
				(Bclr 74)
				(HandsOn)
				(= global103 0)
				(curRoom newRoom: 48)
			)
		)
	)
)

(instance grabCedScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: MoveTo (+ (theBoat x?) 11) (- (theBoat y?) 6) self
				)
			)
			(1
				(cedric z: 1000)
				(self dispose:)
			)
		)
	)
)

(instance depositCedScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: MoveTo (+ (theBoat x?) 5) (- (theBoat y?) 8) self
				)
			)
			(1
				(ego
					view: 667
					setLoop: 0
					setMotion: MoveTo (- (theBoat x?) 11) (theBoat y?) self
				)
			)
			(2
				(cedric
					init:
					ignoreActors: 1
					posn: (- (theBoat x?) 9) (+ (theBoat y?) 5) 0
					view: 650
					setLoop: 2
					setCel: 6
				)
				(ego setLoop: 1 setCycle: KQ5SyncWalk)
				(= cycles 1)
			)
			(3 (self dispose:))
		)
	)
)

(instance getShellScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (ego view?) 654)
					(self setScript: getOffBoatScript self)
				else
					(= cycles 1)
				)
			)
			(1
				(Load rsVIEW 665)
				(ego
					setMotion: PolyPath (+ (shell x?) 1) (+ (shell y?) 1) self
				)
			)
			(2
				(= local135 1)
				(= register (ego view?))
				(if (== (ego view?) 6) ((ego head?) hide:))
				(ego
					normal: 0
					view: 665
					setLoop: 1
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(3
				(shell dispose:)
				(glint dispose:)
				(ego setCycle: End self)
			)
			(4
				(if (== (ego view?) 6) ((ego head?) show:))
				(ego
					normal: 1
					view: register
					setLoop: -1
					setCycle: KQ5SyncWalk
					get: 23
				)
				(SolvePuzzle 2)
				(proc0_29 9068)
				(= local135 0)
				(HandsOn)
				(= global103 0)
				(self dispose:)
			)
		)
	)
)

(instance glintScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(glint stopUpd:)
				(= seconds 5)
			)
			(1 (glint setCycle: End self))
			(2
				(glint setCycle: 0)
				(self init:)
			)
		)
	)
)

(instance getEgo1 of Path
	(properties)
	
	(method (at param1)
		(return [local0 param1])
	)
)

(instance getEgo2 of Path
	(properties)
	
	(method (at param1)
		(return [local3 param1])
	)
)

(instance getEgo3 of Path
	(properties)
	
	(method (at param1)
		(return [local8 param1])
	)
)

(instance getEgo4 of Path
	(properties)
	
	(method (at param1)
		(return [local13 param1])
	)
)

(instance takeEgo of Path
	(properties)
	
	(method (at param1)
		(return [local18 param1])
	)
)

(instance getCed of Path
	(properties)
	
	(method (at param1)
		(return [local27 param1])
	)
)

(instance takeCed of Path
	(properties)
	
	(method (at param1)
		(return [local38 param1])
	)
)

(instance guard of Path
	(properties)
	
	(method (at param1)
		(return [local47 param1])
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)

(instance poly4 of Polygon
	(properties
		type $0002
	)
)

(instance harpy1 of Actor
	(properties
		x -11
		y 38
		view 654
		priority 12
		signal $6880
		illegalBits $0000
	)
)

(instance harpy2 of Actor
	(properties
		x -12
		y 58
		view 654
		signal $6800
		illegalBits $0000
	)
)

(instance harpy3 of Actor
	(properties
		x -13
		y 49
		view 654
		signal $6800
		illegalBits $0000
	)
)

(instance cedric of Actor
	(properties
		view 654
		loop 2
		signal $7800
	)
)

(instance theBoat of Actor
	(properties
		x -35
		y 182
		yStep 1
		view 650
		loop 2
		signal $7900
		detailLevel 3
		xStep 1
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 538)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom
						setScript: castOffScript 0 (== (ego view?) 661)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance wake of Prop
	(properties
		view 633
		signal $6000
	)
)

(instance sail of Prop
	(properties
		x 146
		y 133
		view 649
		loop 2
		signal $7900
	)
)

(instance shell of Prop
	(properties
		x 120
		y 100
		view 652
		loop 3
		signal $6000
		detailLevel 3
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 539)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getShellScript)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance glint of Prop
	(properties
		view 652
		loop 4
		signal $6000
		cycleSpeed 3
		detailLevel 3
	)
)

(instance wave0 of Prop
	(properties
		x 33
		y 146
		view 652
		priority 1
		signal $4010
		cycleSpeed 15
		detailLevel 3
	)
	
	(method (handleEvent)
	)
)

(instance wave1 of Prop
	(properties
		x 108
		y 154
		view 652
		loop 1
		priority 1
		signal $4010
		cycleSpeed 15
		detailLevel 3
	)
	
	(method (handleEvent)
	)
)

(instance wave2 of Prop
	(properties
		x 164
		y 180
		view 652
		loop 2
		priority 1
		signal $4010
		cycleSpeed 15
		detailLevel 3
	)
	
	(method (handleEvent)
	)
)

(instance beach of RFeature
	(properties)
	
	(method (handleEvent event)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bt       code_1bc3
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      16384
			eq?     
			not     
			bt       code_1bc3
			pushi    3
			pushi    4
			dup     
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callk    OnControl,  6
			push    
			ldi      4
			and     
			bt       code_1bbf
			pushi    3
			pushi    4
			dup     
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callk    OnControl,  6
			push    
			ldi      64
			and     
			bt       code_1bbf
			pushi    3
			pushi    4
			dup     
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callk    OnControl,  6
			push    
			ldi      8192
			and     
			bt       code_1bbf
			pushi    3
			pushi    4
			dup     
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callk    OnControl,  6
			push    
			ldi      1024
			and     
code_1bbf:
			not     
			bnt      code_1bc7
code_1bc3:
			ret     
			jmp      code_1be6
code_1bc7:
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      2
			eq?     
			bnt      code_1be5
			pushi    1
			pushi    540
			callb    proc0_29,  2
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
code_1be5:
			toss    
code_1be6:
			ret     
		)
	)
)

(instance arch of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0010))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 541)
					(event claimed: 1)
				)
			)
		)
	)
)
