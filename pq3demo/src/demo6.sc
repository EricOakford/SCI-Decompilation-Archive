;;; Sierra Script 1.0 - (do not remove this comment)
(script# 125)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demo6 0
)

(local
	mD1
	mD2
)
(procedure (JimSays theString &tmp [temp0 3])
	(if mD1
		(Display 125 0 p_restore mD1)
		(Display 125 0 p_restore mD2)
		(= mD1 0)
	)
	(if theString
		(= mD2
			(Display theString
				p_width 300
				p_at 10 5
				p_mode teJustLeft
				p_font 11
				p_color 0
				p_save
			)
		)
		(= mD1
			(Display theString
				p_width 300
				p_at 10 5
				p_mode teJustLeft
				p_font 9
				p_color 7
				p_save
			)
		)
	)
)

(instance fakeEgo of Actor
	(properties)
)

(instance demo6 of Room
	(properties
		picture 55
		style FADEOUT
	)
	
	(method (init)
		(fakeEgo
			view: 15
			loop: 0
			cel: 3
			posn: 110 95
			setStep: 3 2
			illegalBits: cWHITE
			init:
		)
		(super init:)
		(LoadMany FONT 9 11)
		(HandsOff)
		(Load rsSOUND 558)
		(addToPics add: pCar_a pCar2 doit:)
		(frontDoor init:)
		(halfTrack init:)
		(music number: 551 vol: 127 loop: -1 playBed:)
		(topLights setCycle: Forward init:)
		(rearLights setCycle: Forward init:)
		(dog setCycle: Forward init:)
		(self setScript: demoScript)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
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
				(JimSays {...as well as the brutally sadistic.})
				(= seconds 3)
			)
			(2
				(JimSays 0)
				(JimSays
					{Only you can find the clues that will lead Sonny to his wife's twisted assailants...}
				)
				(fakeEgo setCycle: BegLoop self)
			)
			(3
				(sfx2 number: 557 loop: -1 play:)
				(halfTrack setMotion: MoveTo 73 142 self)
				(frontWheel setCycle: Forward)
				(backWheel setCycle: Forward)
			)
			(4
				(JimSays 0)
				(JimSays
					{...clues that will put Sonny in the line of fire in the greatest showdown of his career.}
				)
				(dog loop: 1 cel: 0 setCycle: EndLoop)
				(halfTrack setScript: jiggle)
				(= seconds 2)
			)
			(5
				(dog stopUpd:)
				(frontDoor setCycle: EndLoop self)
				(halfTrack
					setPri: 6
					setScript: 0
					moveSpeed: 8
					setMotion: MoveTo 89 134 self
				)
				(sfx1 number: 558 loop: 1 play:)
			)
			(6)
			(7
				(sfx2 number: 557 loop: -1 play:)
				(frontDoor stopUpd:)
				(halfTrack moveSpeed: 7 setMotion: MoveTo 0 171 self)
			)
			(8
				(frontWheel setCycle: 0)
				(backWheel setCycle: 0)
				(JimSays 0)
				(sfx2 fade: self)
			)
			(9 (curRoom newRoom: 126))
		)
	)
)

(instance jiggle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(halfTrack posn: 73 144)
				(= cycles 1)
			)
			(1
				(halfTrack posn: 73 142)
				(= cycles 1)
				(= state -1)
			)
		)
	)
)

(instance frontDoor of Prop
	(properties
		x 144
		y 84
		view 581
		signal (| ignrAct stopUpdOn)
		cycleSpeed 8
	)
)

(instance dog of Actor
	(properties
		x 31
		y 59
		view 579
		signal (| ignrAct fixedLoop)
	)
)

(instance pCar_a of PicView
	(properties
		x 185
		y 211
		view 1001
		loop 1
	)
)

(instance halfTrack of Actor
	(properties
		y 171
		view 581
		loop 1
		signal (| ignrAct fixedLoop)
		moveSpeed 7
	)
	
	(method (init)
		(super init:)
		(frontWheel init:)
		(backWheel init:)
	)
)

(instance frontWheel of Actor
	(properties
		x 29
		y 159
		view 581
		loop 2
		signal (| ignrAct fixedLoop)
		cycleSpeed 9
	)
	
	(method (doit)
		(super doit:)
		(self
			x: (+ (halfTrack x?) 30)
			y: (- (halfTrack y?) 16)
		)
	)
)

(instance backWheel of Actor
	(properties
		x 29
		y 159
		view 581
		loop 2
		cel 2
		signal (| ignrAct fixedLoop)
		cycleSpeed 9
	)
	
	(method (doit)
		(super doit:)
		(self x: (- (halfTrack x?) 12) y: (halfTrack y?))
	)
)

(instance pCar_b of Prop
	(properties
		x 185
		y 211
		view 1001
		loop 1
		signal (| ignrAct fixedLoop stopUpdOn)
	)
)

(instance rearLights of Prop
	(properties
		x 166
		y 188
		view 1001
		loop 2
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 7
	)
)

(instance topLights of Prop
	(properties
		x 181
		y 172
		view 1001
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 7
	)
)

(instance pCar2 of PicView
	(properties
		x -30
		y 131
		view 1001
		loop 1
		priority 1
	)
)

(instance sfx1 of Sound
	(properties
		priority 17
	)
)

(instance sfx2 of Sound
	(properties
		priority 16
	)
)
