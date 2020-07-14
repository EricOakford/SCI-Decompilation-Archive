;;; Sierra Script 1.0 - (do not remove this comment)
(script# 124)
(include game.sh)
(use Main)
(use RandCyc)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demo5 0
)

(local
	mD1
	mD2
)
(procedure (PersonSays theString &tmp [temp0 3])
	(if mD1
		(Display 124 0 p_restore mD1)
		(Display 124 0 p_restore mD2)
		(= mD1 0)
	)
	(if theString
		(= mD2
			(Display theString
				p_width 130
				p_at 10 5
				p_mode teJustLeft
				p_font 11
				p_color 0
				p_save
			)
		)
		(= mD1
			(Display theString
				p_width 130
				p_at 10 5
				p_mode teJustLeft
				p_font 9
				p_color 7
				p_save
			)
		)
	)
)

(instance demo5 of Room
	(properties
		picture 1000
		style FADEOUT
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(LoadMany FONT 9 11)
		(LoadMany VIEW 16 336)
		(suspectCar init:)
		(pCar init:)
		(ego normal: 1 view: 1 setCycle: Walk posn: -74 183 init:)
		(self setScript: demoScript)
		(sfx1 number: 294 loop: -1 play:)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
	)
	
	(method (dispose)
		(DisposeScript RANDCYC)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(else (event claimed: TRUE))
		)
	)
)

(instance demoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(sfx2 number: 298 loop: 1 play:)
				(pCar setMotion: MoveTo 96 207 self)
			)
			(2
				(pCar setPri: -1 stopUpd:)
				(= seconds 3)
			)
			(3
				(ego
					normal: 0
					view: 16
					loop: 2
					cel: 10
					posn: 83 181
					setPri: 3
					setCycle: BegLoop self
					init:
				)
			)
			(4
				(sfx2 number: 901 loop: 1 vol: 127 play:)
				(ego
					normal: 1
					view: 1
					setCycle: Walk
					setLoop: 6
					posn: 74 183
					setMotion: MoveTo 146 156 self
				)
			)
			(5
				(music
					number: 291
					vol: 40
					loop: -1
					playBed:
					fade: 117 40 10 0
				)
				(ego setMotion: MoveTo 250 117 self)
			)
			(6
				(ego loop: 5 stopUpd:)
				(suspect init:)
				(= cycles 2)
			)
			(7
				(suspectMouth setCycle: RandCycle)
				(PersonSays
					{"Say baby," he says..."Where in the world did ya'come from? Ya' must have fell in from out of the sky, cause I never saw ya!"}
				)
				(= seconds 7)
			)
			(8
				(suspectHead setScript: grooveScript)
				(suspectMouth dispose:)
				(PersonSays
					{"You're driving just a bit fast," you explain. "I need your driver's license please."}
				)
				(= seconds 5)
			)
			(9
				(suspectMouth setCycle: 0 cel: 0)
				(PersonSays 0)
				(music fade: self)
				(sfx1 fade: self)
			)
			(10 (curRoom newRoom: 125))
		)
	)
)

(instance grooveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo 189 45 self)
			)
			(1
				(client setMotion: MoveTo 191 45 self)
				(= state -1)
			)
		)
	)
)

(instance suspectCar of View
	(properties
		x 253
		y 150
		view 1001
		signal (| ignrAct stopUpdOn)
	)
)

(instance pCar of Actor
	(properties
		x 26
		y 233
		view 1001
		loop 1
		priority 13
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (init)
		(super init:)
		(rearLights setCycle: Forward init:)
		(topLights setCycle: Forward init:)
	)
)

(instance rearLights of Actor
	(properties
		x 7
		y 210
		view 1001
		loop 2
		cel 2
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 7
	)
	
	(method (doit)
		(super doit:)
		(self x: (- (pCar x?) 19) y: (- (pCar y?) 23))
	)
)

(instance topLights of Actor
	(properties
		x 22
		y 194
		view 1001
		loop 3
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 8
	)
	
	(method (doit)
		(super doit:)
		(self x: (- (pCar x?) 4) y: (- (pCar y?) 39))
	)
)

(instance suspect of View
	(properties
		x 190
		y 45
		view 336
		priority 12
		signal (| ignrAct stopUpdOn)
	)
	
	(method (init)
		(super init:)
		(suspectHead posn: (self x?) (self y?) init:)
		(suspectMouth posn: (self x?) (self y?) init:)
		(suspectEye posn: (self x?) (- (self y?) 5) init:)
	)
)

(instance suspectHead of Actor
	(properties
		view 336
		cel 1
		priority 13
		signal (| ignrAct fixedLoop fixedCel fixPriOn stopUpdOn)
		xStep 1
		moveSpeed 9
	)
	
	(method (doit)
		(super doit:)
		(suspectMouth posn: (self x?) (self y?) forceUpd:)
		(suspectEye posn: (self x?) (- (self y?) 5) forceUpd:)
	)
)

(instance suspectMouth of Prop
	(properties
		view 336
		loop 2
		priority 13
		signal $4011
		cycleSpeed 9
	)
)

(instance suspectEye of Prop
	(properties
		view 336
		loop 1
		priority 14
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doit)
		(super doit:)
		(if (< (Random 0 100) 3) (self setCycle: BegLoop))
	)
)

(instance sfx1 of Sound
	(properties
		priority 15
	)
)

(instance sfx2 of Sound
	(properties
		priority 14
	)
)
