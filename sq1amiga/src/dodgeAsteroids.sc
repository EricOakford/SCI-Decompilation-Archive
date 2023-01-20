;;; Sierra Script 1.0 - (do not remove this comment)
(script# ASTEROIDS) ;401
(include game.sh)
(use Main)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	dodgeAsteroids 0
	blowUpShip 1
)

(local
	local0
	local1
)
(instance dodgeAsteroids of Script	
	(method (doit)
		(if (not script)
			(cond 
				((and (!= local0 3) (< (ship y?) 23))
					(= local0 3)
					(self cue:)
				)
				((and (not register) (>= (ast4 x?) 210))
					(= local1 1)
					(self setScript: blowUpShip)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(asts
			eachElementDo: #dispose
			eachElementDo: #delete
			release:
		)
		(ship setCycle: 0 setMotion: 0 dispose: delete:)
		(= local0 0)
		(= register 0)
		(super dispose: &rest)
		(DisposeScript ASTEROIDS)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic pause: TRUE)
				(theMusic2 number: 473 loop: -1 play:)
				((ScriptID 49 7) number: 533 loop: 1 play:)
				(curRoom drawPic: 17 (| BLACKOUT DISSOLVE))
				(cast eachElementDo: #hide eachElementDo: #stopUpd)
				(= local0 2)
				(ast1 init: setCycle: Forward)
				(ast2 init: setCycle: Reverse)
				(ast3 init: setCycle: Reverse)
				(ast4 init: setCycle: Forward)
				(ast5 init: setCycle: Forward)
				(asts add: ast1 ast2 ast3 ast4 ast5)
				(ship
					init:
					posn: 292 160
					setLoop: 0
					setStep: 3 1
					setCycle: Forward
				)
			)
			(1
				(ship setMotion: MoveTo -55 -45 self)
			)
			(2
				((ScriptID 49 7) fade:)
				(theMusic2 stop:)
				(theMusic pause: FALSE)
				(self dispose:)
			)
		)
	)
)

(instance blowUpShip of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 49 7) stop:)
				(theMusic number: 411 loop: 1 play:)
				(ship
					view: 156
					loop: 5
					cel: 0
					cycleSpeed: 9
					setCycle: EndLoop self
				)
			)
			(1
				(= seconds 2)
			)
			(2
				(EgoDead 948 0 0 401 0)
			)
		)
	)
)

(instance asts of Collection)

(instance closeCode of Code
	(method (doit who)
		(InRect
			(- (ship x?) 100)
			(- (ship y?) 50)
			(ship x?)
			(ship y?)
			who
		)
	)
)

(instance ship of Actor
	(properties
		x 142
		y 213
		yStep 4
		view 156
		priority 14
		signal (| ignrHrz fixedLoop fixPriOn) ;$2810
		cycleSpeed 3
		illegalBits $0000
		xStep 4
		moveSpeed 3
	)
	
	(method (doit &tmp obj)
		(cond 
			((or local1 (not local0) (== local0 1) mover) 0)
			((= obj (asts firstTrue: #perform closeCode))
				(if
					(and
						(- x (obj x?))
						(<
							150
							(/ (* 200 (- y (obj y?))) (- x (obj x?)))
						)
					)
					(self setMotion: MoveTo (- x 10) (- y (Random 3 7)))
				else
					(self setMotion: MoveTo (+ x 5) (- y (Random 6 10)))
				)
			)
			((< 63 (/ (* y 100) x)) (= x (+ x 2)))
			((> 57 (/ (* y 100) x)) (= x (- x 2)))
			(else (-- y) (-- x))
		)
		(super doit:)
	)
)

(instance ast1 of Actor
	(properties
		yStep 12
		view 118
		loop 1
		cel 4
		signal (| ignrAct ignrHrz fixedLoop)
		cycleSpeed 4
		xStep 12
		moveSpeed 4
	)
	
	(method (init)
		(= x -340)
		(= y -162)
		(self setMotion: MoveTo 294 218 self)
		(super init: &rest)
	)
	
	(method (cue)
		(if (!= local0 3)
			(self init:)
		)
	)
)

(instance ast2 of Actor
	(properties
		x 126
		y 56
		yStep 10
		view 118
		cel 8
		signal (| ignrAct ignrHrz fixedLoop)
		cycleSpeed 4
		xStep 10
		moveSpeed 4
	)
	
	(method (init)
		(= x -194)
		(= y -134)
		(self setMotion: MoveTo 346 187 self)
		(super init: &rest)
	)
	
	(method (cue)
		(if (!= local0 3)
			(self init:)
		)
	)
)

(instance ast3 of Actor
	(properties
		x 203
		y 68
		yStep 8
		view 118
		loop 1
		cel 7
		signal (| ignrAct ignrHrz fixedLoop)
		cycleSpeed 4
		xStep 8
		moveSpeed 4
	)
	
	(method (init)
		(= x -117)
		(= y -142)
		(self setMotion: MoveTo 342 137 self)
		(super init: &rest)
	)
	
	(method (cue)
		(if (!= local0 3)
			(self init:)
		)
	)
)

(instance ast4 of Actor
	(properties
		x 82
		y 102
		yStep 11
		view 118
		loop 2
		signal (| ignrAct ignrHrz fixedLoop)
		cycleSpeed 4
		xStep 11
		moveSpeed 4
	)
	
	(method (init)
		(= x -32)
		(= y -88)
		(self setMotion: MoveTo 402 292 self)
		(super init: &rest)
	)
	
	(method (cue)
		(if (!= local0 3)
			(self init:)
		)
	)
)

(instance ast5 of Actor
	(properties
		yStep 7
		view 118
		cel 3
		signal (| ignrAct ignrHrz fixedLoop)
		cycleSpeed 4
		xStep 7
		moveSpeed 4
	)
	
	(method (init)
		(= x -320)
		(= y -110)
		(self
			setLoop: (Random 0 2)
			setMotion: MoveTo 164 240 self
		)
		(super init: &rest)
	)
	
	(method (cue)
		(if (!= local0 3)
			(self init:)
		)
	)
)
