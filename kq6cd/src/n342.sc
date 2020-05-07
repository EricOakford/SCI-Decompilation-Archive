;;; Sierra Script 1.0 - (do not remove this comment)
(script# 342)
(include sci.sh)
(use Main)
(use Conv)
(use Scaler)
(use PolyPath)
(use StopWalk)
(use Motion)
(use Actor)
(use System)

(public
	proc342_0 0
	proc342_1 1
	proc342_2 2
)

(local
	local0
	local1
)
(procedure (proc342_0)
	(shieldGuard
		posn: 62 113
		loop: 2
		init:
		stopUpd:
		setCycle: StopWalk -1
	)
	(spearGuard
		posn: 134 108
		loop: 2
		init:
		stopUpd:
		setCycle: StopWalk -1
	)
	(bird init: stopUpd:)
	(curRoom setScript: celesteRescue)
)

(procedure (proc342_1)
	(curRoom setScript: flyIn)
)

(procedure (proc342_2)
	(shieldGuard
		posn: 285 155
		loop: 1
		init:
		setStep: 3 2
		cycleSpeed: 0
		moveSpeed: 0
		setCycle: StopWalk -1
	)
	(spearGuard
		posn: 135 155
		loop: 0
		init:
		setStep: 3 2
		cycleSpeed: 0
		moveSpeed: 0
		setCycle: StopWalk -1
	)
	(theGame handsOff:)
	(curRoom setScript: toGehenna)
)

(instance cliffTalk of Conversation
	(properties)
)

(instance celeste of Actor
	(properties
		yStep 3
		signal $7000
		illegalBits $0000
		xStep 5
	)
)

(instance shieldGuard of Actor
	(properties
		yStep 3
		view 344
		signal $7000
		illegalBits $0000
		xStep 5
	)
)

(instance spearGuard of Actor
	(properties
		yStep 3
		view 343
		signal $7000
		illegalBits $0000
		xStep 5
	)
)

(instance bird of Actor
	(properties
		x 141
		y 142
		yStep 8
		view 342
		priority 14
		signal $7010
		illegalBits $0000
		xStep 14
	)
)

(instance eyeGlint of Prop
	(properties
		view 902
	)
	
	(method (init)
		(self setPri: 15 cycleSpeed: 6)
		(super init:)
	)
)

(instance flyer of Actor
	(properties
		x 230
		y -15
		view 353
		signal $6000
	)
)

(instance glintScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(client show: setCycle: End self)
			)
			(2 (client setCycle: Beg self))
			(3
				(client hide:)
				(= seconds (Random 2 5))
			)
			(4 (self start: 1 init:))
		)
	)
)

(instance celesteRescue of Script
	(properties)
	
	(method (doit)
		(if local0
			(shieldGuard
				x: (- (spearGuard x?) 26)
				y: (- (spearGuard y?) 10)
			)
			(ego
				x: (+ (shieldGuard x?) 16)
				y: (+ (shieldGuard y?) 35)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(eyeGlint
					init:
					posn: (+ (bird x?) 2) (- (bird y?) 11)
					setScript: glintScript
				)
				(messager say: 1 0 11 1 self 340)
			)
			(2
				(shieldGuard stopUpd:)
				(spearGuard stopUpd:)
				(soundFx2 number: 909 setLoop: 1 play:)
				((ScriptID 340 2) setMotion: MoveTo 0 149 self)
			)
			(3
				((ScriptID 340 2) addToPic: dispose:)
				(= seconds 2)
			)
			(4
				(UnLoad 128 340)
				(celeste
					view: 361
					setLoop: 2
					cel: 0
					posn: 5 138
					setPri: 8
					init:
					setCycle: 0
					setMotion: MoveTo 20 138 self
				)
				(= ticks 6)
			)
			(5
				(celeste cel: 1 posn: 22 138)
				(= ticks 6)
			)
			(6
				(celeste cel: 2 posn: 26 138)
				(= ticks 6)
			)
			(7
				(celeste cel: 3 posn: 32 138)
				(= ticks 6)
			)
			(8
				(celeste cel: 4 posn: 36 139)
				(= ticks 6)
			)
			(9
				(celeste cel: 5 posn: 38 139)
				(= ticks 6)
			)
			(10
				(celeste
					view: 364
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 39 139 self
				)
				(UnLoad 128 361)
			)
			(11
				(celeste setMotion: MoveTo 89 139 self)
			)
			(12
				(spearGuard
					startUpd:
					view: 363
					setLoop: 1
					setCycle: CT 3 1 self
				)
				(shieldGuard
					startUpd:
					view: 363
					setLoop: 0
					setCycle: CT 3 1
				)
			)
			(13
				(shieldGuard stopUpd:)
				(spearGuard stopUpd:)
				(celeste setPri: 15)
				(ego show: ignoreActors: 1 setPri: 8 setCycle: End self)
			)
			(14
				(ego reset: 0 posn: 22 138 setMotion: MoveTo 63 148 self)
			)
			(15
				(spearGuard startUpd: view: 363 setCycle: End self)
				(shieldGuard startUpd: view: 363 setCycle: End)
			)
			(16 (= ticks 12))
			(17
				(cliffTalk
					add: 340 1 0 11 2
					add: 340 1 0 11 3
					add: 340 1 0 11 4
					add: 340 1 0 11 5
					init: self
				)
			)
			(18
				(shieldGuard stopUpd:)
				(spearGuard stopUpd:)
				(ego stopUpd:)
				(celeste
					view: 365
					setLoop: 0
					cel: 0
					posn: 96 69
					setCycle: End self
				)
				(UnLoad 128 364)
			)
			(19
				(celeste
					view: 3651
					setLoop: 0
					cel: 0
					posn: 103 109
					setStep: 10 5
					setCycle: Fwd
					setMotion: MoveTo 228 82 self
				)
				(UnLoad 128 365)
			)
			(20
				(soundFx2 number: 344 setLoop: 1 play:)
				(eyeGlint dispose:)
				(bird
					setLoop: 1
					setSpeed: 3
					xStep: 10
					setCycle: Fwd
					setMotion: MoveTo 335 100 self
				)
				(celeste
					setLoop: -1
					setScale: Scaler 100 10 69 40
					y: (- (celeste y?) 12)
					setMotion: MoveTo 246 40 self
				)
			)
			(21 0)
			(22
				(celeste dispose:)
				(ego setMotion: PolyPath 149 136 self)
			)
			(23
				(bird dispose:)
				(ego setHeading: 0)
				(= cycles 8)
			)
			(24
				(shieldGuard
					view: 344
					setCycle: StopWalk -1
					setLoop: -1
					setMotion: MoveTo (- (ego x?) 26) (- (ego y?) 2) self
				)
				(spearGuard
					view: 343
					setCycle: StopWalk -1
					setLoop: 2
					setMotion: MoveTo (+ (ego x?) 29) (- (ego y?) 2) self
				)
			)
			(25 0)
			(26
				(shieldGuard dispose:)
				(spearGuard dispose:)
				(ego
					normal: 0
					view: 3510
					setLoop: 0
					cel: 0
					setPri: 13
					posn: (ego x?) (+ (ego y?) 4)
					setCycle: End self
				)
				(UnLoad 128 900)
			)
			(27
				(ego
					view: 352
					setStep: 15 12
					cycleSpeed: 6
					moveSpeed: 6
					setLoop: 0
					cel: 0
					setCycle: End self
				)
				(UnLoad 128 3510)
			)
			(28
				(ego
					view: 353
					setCycle: Fwd
					posn: (ego x?) (- (ego y?) 40)
					setMotion: MoveTo (ego x?) -50 self
				)
				(UnLoad 128 3512)
			)
			(29
				(theMusic fade: 0 20 15)
				(curRoom newRoom: 370 -32758)
			)
		)
	)
)

(instance toGehenna of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 3011
					setLoop: 6
					setPri: 15
					cel: 0
					normal: 0
					posn: 257 237
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(1
				(ego cel: 0 posn: 228 223 setCycle: End self)
			)
			(2
				(ego cel: 0 posn: 199 209 setCycle: End self)
			)
			(3
				(ego cel: 0 posn: 175 195 setCycle: End self)
			)
			(4
				(ego
					view: 3011
					setLoop: 1
					cel: 5
					posn: 164 194
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(5
				(ego view: 301 setLoop: 0 cel: 2 normal: 0 posn: 161 185)
				(= cycles 4)
			)
			(6
				(ego cel: 3 posn: 161 185)
				(= cycles 4)
			)
			(7
				(ego cel: 4 posn: 161 185)
				(= cycles 4)
			)
			(8
				(ego
					posn: 162 169
					reset: 6
					setScale: Scaler 100 5 105 65
					setPri: -1
					setMotion: MoveTo 214 167 self
				)
			)
			(9 (ego setHeading: 0 self))
			(10 (= cycles 6))
			(11
				(messager say: 1 0 2 1 self 340)
			)
			(12
				(shieldGuard
					setCycle: StopWalk -1
					setMotion: MoveTo 235 162
				)
				(spearGuard
					setCycle: StopWalk -1
					setMotion: MoveTo 196 162 self
				)
			)
			(13 (= seconds 2))
			(14
				(client setScript: tossEmIn 0 0)
			)
		)
	)
)

(instance flyIn of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local1 1)
				(ego
					view: 3511
					setPri: 14
					setLoop: 0
					setStep: 12 8
					posn: 340 -40
					setCycle: Fwd
					setMotion: MoveTo 245 53 self
				)
			)
			(1
				(ego view: 3512 cel: 0 posn: 212 88 setCycle: End self)
				(UnLoad 128 3511)
			)
			(2
				(ego
					reset: 1
					cel: 4
					posn: 170 128
					setMotion: MoveTo 145 128 self
				)
				(UnLoad 128 3512)
				(shieldGuard
					view: 344
					posn: 186 126
					loop: 1
					init:
					setCycle: Walk
					illegalBits: 0
					setMotion: MoveTo 181 126
				)
				(spearGuard
					view: 343
					posn: 193 129
					loop: 1
					init:
					setCycle: Walk
					illegalBits: 0
					setMotion: MoveTo 188 129
				)
			)
			(3
				(curRoom setScript: tossEmIn 0 1)
			)
		)
	)
)

(instance tossEmIn of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(shieldGuard
					view: 344
					setLoop: -1
					setStep: 3 2
					setSpeed: 4
					setCycle: StopWalk -1
					setMotion: MoveTo 139 109 self
				)
				(UnLoad 128 349)
				(ego setLoop: -1 setSpeed: 4 setMotion: MoveTo 111 110)
				(spearGuard
					view: 343
					setLoop: -1
					setStep: 3 2
					setSpeed: 4
					setCycle: StopWalk -1
					setMotion: MoveTo 85 109 self
				)
				(UnLoad 128 348)
			)
			(1
				(shieldGuard setHeading: 270)
			)
			(2
				(ego setHeading: 0)
				(spearGuard setHeading: 90)
				(= seconds 2)
			)
			(3
				(spearGuard
					view: 362
					setPri: 13
					setLoop: 1
					cel: 0
					setCycle: End self
				)
			)
			(4
				(spearGuard setCycle: Beg)
				(soundFx2 number: 342 setLoop: 1 play:)
				((ScriptID 340 1) setCycle: End self)
			)
			(5
				(if register
					(messager say: 1 0 9 0 self 340)
				else
					(cliffTalk
						add: 340 1 0 9 2
						add: 340 1 0 9 3
						add: 340 1 0 9 4
						add: 340 1 0 9 5
						init: self
					)
				)
			)
			(6
				(spearGuard hide:)
				(shieldGuard hide:)
				(ego
					view: 362
					setLoop: 0
					cel: 0
					posn: 111 115
					normal: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(7
				(ego hide:)
				(spearGuard show:)
				(shieldGuard show:)
				((ScriptID 340 1) setCycle: Beg self)
			)
			(8
				(soundFx2 number: 403 setLoop: 1 play: self)
			)
			(9
				(if (== prevRoomNum 370) (Cursor showCursor: 1))
				(curRoom newRoom: 405)
			)
		)
	)
)
