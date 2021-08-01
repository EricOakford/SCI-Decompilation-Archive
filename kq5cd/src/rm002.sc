;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm002 0
)

(local
	gEgoView
	local1
	local2
	[local3 12] = [319 48 223 77 103 72 183 51 247 0 319]
	[local15 22] = [107 63 78 71 132 90 159 90 180 97 149 109 0 121 0 0 238 0 196 27 140 55]
	[local37 10] = [0 155 103 155 139 171 140 189 0 189]
	[local47 12] = [319 189 286 189 141 125 142 118 239 83 319 81]
	[local59 8] = [138 166 178 165 185 173 143 172]
	local67
	[local68 9] = [1003 215 40 4 11 25 23 31 31]
	[local77 9] = [1000 80 10 4 11 24 19 23 30]
	[local86 9] = [1009 235 20 5 11 24 18 24 23]
)
(instance rm002 of KQ5Room
	(properties
		picture 2
		horizon 45
		north 1
		east 29
		south 3
		west 7
	)
	
	(method (init)
		(super init:)
		(= global320 143)
		(= global321 48)
		(= global325 3023)
		(ego normal: 1 setStep: 3 2 view: 0)
		(self
			setFeatures: mountPath room
			setRegions: 202
			addObstacle: poly1 poly2 poly3 poly4 poly5
		)
		(if (!= (theMusic number?) 24)
			(theMusic number: 24 vol: 127 loop: -1 play:)
		)
		(switch prevRoomNum
			(west
				(ego posn: 11 135)
				(self setScript: (ScriptID 202 1))
			)
			(east
				(ego posn: 311 57)
				(self setScript: (ScriptID 202 1))
			)
			(north
				(ego view: 2 posn: 176 51)
				(self setScript: (ScriptID 202 1))
			)
			(south
				(ego posn: 214 186)
				(self setScript: (ScriptID 202 1))
			)
			(else  (ego posn: 214 186))
		)
		(ego illegalBits: -32768 init:)
		(if (not (Btst 47))
			(snake cycleSpeed: 4 cel: 0 init: stopUpd:)
			(if (not (Btst 87))
				(Bset 87)
				(= local67 1)
				(snake setScript: warnScript)
			)
		)
		(poly1 points: @local3 size: 6)
		(poly2 points: @local15 size: 11)
		(poly3 points: @local37 size: 5)
		(poly4 points: @local47 size: 6)
		(poly5 points: @local59 size: 4)
	)
	
	(method (doit &tmp temp0)
		(cond 
			((& (ego onControl: 1) $4000) (ego view: 2))
			((& (ego onControl: 1) $2000) (ego view: 0))
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				((ScriptID 202 2) register: (ego edgeHit?))
				(self setScript: (ScriptID 202 2))
			)
			((Btst 15) (proc0_29 177) (Bclr 15))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(else
				(switch (event message?)
					(JOY_DOWNRIGHT
						(if
							(and
								(== (inventory indexOf: (theIconBar curInvIcon?)) 34)
								(MousedOn ego event)
							)
							(DoDisplay 0)
							(if (cast contains: snake)
								(Bset 47)
								(SolvePuzzle 3)
								(HandsOff)
								(curRoom setScript: shakeTambourine)
							else
								(proc0_29 174)
							)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance warnScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(theMusic3 number: 38 loop: 1 play:)
				(proc762_1 @local77 5500 self)
			)
			(2
				(= local67 0)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance strike of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 27 loop: 1 play:)
				(snake loop: 1 cel: 0 setCycle: CT 2 1 self)
			)
			(1
				(snake setCycle: CT 4 1)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 476
					loop: 5
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(2 (= seconds 2))
			(3
				(= deathMessage 178)
				(EgoDead 243)
			)
		)
	)
)

(instance shakeTambourine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 230 80 self)
			)
			(1
				(= gEgoView (ego view?))
				((ego head?) hide:)
				(ego
					normal: 0
					view: 476
					loop: 3
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(2
				(theMusic3 number: 51 loop: -1 playBed:)
				(ego loop: 4 cycleSpeed: 0 setCycle: Fwd)
				(= cycles 1)
			)
			(3 (= seconds 5))
			(4
				(theMusic4 stop:)
				(theMusic3 stop:)
				(ego loop: 3 cel: 2 cycleSpeed: 2 setCycle: Beg)
				(proc762_1 @local68 5501)
				(snake loop: 2 cel: 0 setCycle: End self)
			)
			(5
				(snake dispose:)
				(ego
					normal: 1
					view: gEgoView
					loop: 7
					cel: 0
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 2)
			)
			(6
				(theMusic4 stop:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance mountPath of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 172)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance snake of Actor
	(properties
		x 298
		y 64
		view 476
		loop 9
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(cond 
			((curRoom script?) (DoDisplay 0) (theMusic4 fade:))
			((> (= temp0 (ego distanceTo: self)) 70)
				(if local2
					(-- local2)
					(self cel: 0 setCycle: 0)
					(DoDisplay 0)
					(theMusic4 stop:)
				)
			)
			((< temp0 30) (DoDisplay 0) (HandsOff) (curRoom setScript: strike))
			((not local2)
				(DoDisplay 0)
				(++ local2)
				(self setCycle: Fwd)
				(theMusic4 number: 38 loop: -1 play:)
			)
		)
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
					(proc0_29 173)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc762_1 @local86 5502)
					(event claimed: 1)
				)
				(JOY_DOWN
					(proc0_29 176)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(34
							(DoDisplay 0)
							(Bset 47)
							(SolvePuzzle 3)
							(HandsOff)
							(curRoom setScript: shakeTambourine)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(proc0_29 175)
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

(instance poly5 of Polygon
	(properties
		type $0002
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event &tmp temp0)
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
					(proc0_29 172)
					(event claimed: 1)
				)
			)
		)
	)
)
