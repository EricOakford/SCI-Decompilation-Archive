;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use Polygon)
(use Chase)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm036 0
)

(local
	[local0 16] = [120 0 126 140 130 151 85 149 73 157 37 157 0 159]
	[local16 26] = [296 147 295 140 165 140 144 139 143 0 319 0 319 189 313 189 311 175 227 169 159 165 158 161 195 148]
	local42
	[local43 9] = [1028 10 100 3 9 26 24 32 32]
)
(procedure (localproc_000e)
	(= [local43 1] (Min (Max 0 (- (ego x?) 40)) 239))
)

(instance rm036 of KQ5Room
	(properties
		picture 36
		horizon 135
		north 38
		west 35
	)
	
	(method (init)
		(super init:)
		(self setFeatures: path36 entrance)
		(switch prevRoomNum
			(north
				(ego posn: 149 140)
				(theMusic number: 5 loop: -1 playBed:)
			)
			(else  (ego posn: 15 169))
		)
		(ego
			view: 10
			setStep: 3 2
			illegalBits: -32768
			heading: 90
			init:
		)
		(if (!= ((inventory at: 2) owner?) 36)
			(yeti
				moveSpeed: 1
				cycleSpeed: 1
				setCycle: Walk
				ignoreActors: 1
				setLoop: 0
				init:
				setScript: chaseEgo
			)
			(Bset 40)
			(theMusic2 number: 110 loop: -1 vol: 127 play:)
		)
		(poly1 points: @local0 size: 8)
		(poly2 points: @local16 size: 13)
		(self addObstacle: poly1 poly2)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if (== temp0 38) (theMusic fade:))
				(curRoom newRoom: temp0)
			)
			((& (ego onControl: 0) $2000)
				(if (cast contains: yeti)
					(yeti setScript: 0 setMotion: 0)
				)
				(HandsOff)
				(self setScript: fallingDown)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 972)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance chaseEgo of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (HaveMouse) (= cycles 10) else (= seconds 3))
			)
			(1
				(theAudio number: 7064 loop: 1 play:)
				(yeti setMotion: MoveTo 168 (yeti y?) self)
			)
			(2
				(yeti setMotion: Chase ego 32 self)
			)
			(3
				(HandsOff)
				(ego setMotion: 0)
				(yeti
					illegalBits: 0
					setPri: (+ (ego priority?) 2)
					setMotion: MoveTo (+ (ego x?) 28) (- (ego y?) 3) self
				)
			)
			(4
				(curRoom setScript: bogusScript)
				(yeti loop: 3 cel: 0 cycleSpeed: 1 setCycle: End self)
			)
			(5
				(if
				(and (>= (ego heading?) 0) (<= (ego heading?) 180))
					(= temp0 5)
				else
					(= temp0 6)
				)
				(ego
					normal: 0
					setPri: (+ (yeti priority?) 1)
					setLoop: temp0
					x: (- (yeti x?) 35)
					y: (- (yeti y?) 2)
					view: 542
					ignoreActors:
					cycleSpeed: 1
					setCycle: Fwd
				)
				((ego head?) hide:)
				(theAudio number: 7064 loop: 1 play:)
				(= seconds 4)
			)
			(6
				(theMusic2 stop:)
				(= deathMessage 470)
				(cls)
				(EgoDead 544)
			)
		)
	)
)

(instance bogusScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
		)
	)
)

(instance throwPie of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (not state) (<= (yeti x?) 250))
			(= cycles 1)
		)
		(if
		(and (== state 2) (<= (- (yeti x?) (thePie x?)) 18))
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DoAudio 1 8111)
				(= local42 1)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 558
					x: (+ (ego x?) 13)
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: CT 6 1 self
				)
			)
			(2
				(HandsOn)
				(ego setCycle: End put: 2 36)
				(HandsOff)
				(thePie
					posn: (+ (ego x?) 31) (- (ego y?) 34)
					setCycle: Walk
					setMotion: MoveTo (yeti x?) (- (yeti y?) 34)
					init:
				)
				(theMusic2 stop:)
				(theMusic3 number: 116 loop: 1 vol: 127 play:)
			)
			(3
				(theAudio number: 8111 loop: 1 play:)
				(thePie dispose:)
				(yeti
					illegalBits: 0
					setLoop: 1
					setScript: 0
					setStep: 2 1
					cycleSpeed: 4
					setCycle: End
					setMotion: MoveTo (yeti x?) 167 self
				)
				(chaseEgo client: 0 dispose:)
			)
			(4
				(theAudio number: 7064 loop: -1 play:)
				(yeti setLoop: 2 setCycle: Beg self)
			)
			(5
				(yeti
					setLoop: 1
					cycleSpeed: 6
					setCycle: End self
					setMotion: MoveTo (+ (yeti x?) 5) (yeti y?)
				)
			)
			(6
				(yeti setLoop: 2 cel: 0 setCycle: Beg self setStep: 4 3)
			)
			(7
				(theAudio stop:)
				(theAudio number: 8115 loop: 1 play:)
				(yeti
					setLoop: 4
					cycleSpeed: 4
					setCycle: End
					setStep: 3 5
					setMotion: MoveTo (yeti x?) 240 self
				)
			)
			(8 (= seconds 4))
			(9
				(theAudio number: 8078 loop: 1 play:)
				(ShakeScreen 7 3)
				(= seconds 3)
			)
			(10
				(theMusic3 number: 0 stop:)
				(ego
					normal: 1
					view: 10
					x: (+ (ego x?) 11)
					loop: 0
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(11
				(HandsOn)
				(SolvePuzzle 4)
				(client setScript: 0)
			)
		)
	)
)

(instance fallingDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego
					normal: 0
					view: 76
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
					illegalBits: 0
					ignoreActors: 1
				)
			)
			(1
				(theAudio loop: 1 number: 7053 play: self)
				(ego yStep: 8 setMotion: MoveTo (- (ego x?) 20) 230)
			)
			(2
				(theAudio number: 8892 loop: 1 play:)
				(= seconds 3)
			)
			(3
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance path36 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $2002))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(if (!= ((inventory at: 2) owner?) 36)
						(SpeakAudio 472)
					else
						(SpeakAudio 473)
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance entrance of RFeature
	(properties
		nsBottom 200
		nsRight 320
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
					(SpeakAudio 474)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(if (not (HaveMouse))
						(switch (inventory indexOf: (theIconBar curInvIcon?))
							(2
								(if (> (ego distanceTo: yeti) 100)
									(HandsOff)
									(curRoom setScript: throwPie)
								else
									(SpeakAudio 477 0 1)
								)
								(event claimed: 1)
							)
							(28 (event claimed: 0))
							(else 
								(SpeakAudio 478)
								(event claimed: 1)
							)
						)
					)
				)
			)
		)
	)
)

(instance thePie of Actor
	(properties
		x -20
		view 558
		loop 1
		priority 14
		signal $2810
		xStep 4
	)
)

(instance yeti of Actor
	(properties
		x 240
		y 149
		yStep 3
		view 542
		signal $4000
		xStep 5
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
					(SpeakAudio 475)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 476)
					(event claimed: 1)
				)
				(JOY_DOWN
					(ego setMotion: 0)
					(localproc_000e)
					(proc762_1 @local43 7024)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(2
							(if (> (ego distanceTo: yeti) 100)
								(HandsOff)
								(curRoom setScript: throwPie)
							else
								(SpeakAudio 477 0 1)
							)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 478)
							(event claimed: 1)
						)
					)
				)
			)
		)
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
