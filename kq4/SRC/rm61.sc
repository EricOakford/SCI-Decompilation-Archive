;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use System)

(public
	Room61 0
)

(local
	local0
)
(instance fallSound of Sound
	(properties
		number 51
	)
)

(instance Room61 of Room
	(properties
		picture 61
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load SOUND 51)
		(Load VIEW 44)
		(Load VIEW 42)
		(Load PICTURE 66)
		(self setRegions: HAUNTED_HOUSE)
		(super init:)
		(= isIndoors TRUE)
		(if (== prevRoomNum 66)
			(ego
				posn: 111 171
				view: 4
				loop: 0
				baseSetter: (ScriptID 0 1)
				xStep: 2
				yStep: 1
				setPri: 12
				illegalBits: cWHITE
				init:
			)
		)
		(if (== prevRoomNum 58)
			(ego
				posn: 155 44
				view: 4
				baseSetter: (ScriptID 0 1)
				looper: myLooper
				xStep: 4
				yStep: 2
				illegalBits: cWHITE
				init:
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl: FALSE) $0040)
				(== (ego script?) 0)
			)
			(ego looper: 0)
			(curRoom newRoom: 66)
		)
		(if
			(and
				(& (ego onControl: FALSE) $0020)
				(== (ego script?) 0)
			)
			(ego looper: 0)
			(curRoom newRoom: 58)
		)
		(if
			(and
				(& (ego onControl: FALSE) $0004)
				(== (ego script?) 0)
			)
			(ego setScript: fallStairs)
		)
		(cond 
			((& (ego onControl: FALSE) $0200) (ego looper: myLooper))
			(
			(and (> (ego y?) 130) (!= (ego script?) fallStairs)) (ego looper: 0))
		)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				(
				(and (== (event type?) saidEvent) (Said 'look>'))
					(cond 
						((Said '/stair') (Print 61 0))
						((Said '/dirt') (Print 61 1))
						((Said '/wall') (Print 61 2))
						((Said '<up[/!*]') (Print 61 3))
						((Said '<down[/!*]') (Print 61 1))
						((Said '[<around][/room,tower]') (Print 61 4))
					)
				)
			)
		)
	)
)

(instance myLooper of Code
	(properties)
	
	(method (doit param1)
		(param1
			loop:
				(cond 
					(
						(or
							(>= (param1 heading?) 335)
							(<= (param1 heading?) 25)
						)
						2
					)
					(
						(and
							(>= (param1 heading?) 155)
							(<= (param1 heading?) 205)
						)
						3
					)
					(
						(and
							(> (param1 heading?) 25)
							(< (param1 heading?) 155)
						)
						0
					)
					(else 1)
				)
		)
	)
)

(instance fallStairs of Script
	(properties)
	
	(method (doit)
		(if
		(and (== (curRoom picture?) 61) (> (ego y?) 100))
			(ego setPri: 14)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 (Print 61 5 #at -1 10 #dispose))
				(User canControl: FALSE)
				(fallSound play:)
				(ego illegalBits: 0 ignoreActors: 1)
				(self cue:)
			)
			(1
				(ego view: 44 moveSpeed: 0 setPri: 13 setCycle: EndLoop self)
			)
			(2
				(ego
					xStep: 5
					yStep: 20
					setCycle: Forward
					setPri: -1
					setMotion:
						MoveTo
						(if (< (ego y?) 130)
							(- (ego x?) 20)
						else
							(+ (ego x?) 20)
						)
						255
						self
				)
			)
			(3
				(ego posn: 195 9)
				(curRoom drawPic: 66)
				(sounds eachElementDo: #stop 0)
				(fallSound play:)
				(ego setPri: -1 setMotion: MoveTo 200 137 self)
			)
			(4
				(cls)
				(ego
					view: 42
					loop: (mod (ego loop?) 2)
					baseSetter: 0
					setPri: 3
				)
				((ScriptID 0 4) setCycle: self 1)
			)
			(5
				(ShakeScreen 10)
				(Animate (cast elements?) FALSE)
				(= seconds 4)
			)
			(6 (= dead TRUE))
		)
	)
)
