;;; Sierra Script 1.0 - (do not remove this comment)
(script# 7)
(include game.sh)
(use Main)
(use Osc)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom7 0
)

(instance demoRoom7 of Room
	(properties
		picture 114
		style IRISIN
	)
	
	(method (init)
		(LoadMany PICTURE 114 410 100)
		(LoadMany VIEW 112 410 411 412)
		(super init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script

	(method (changeState newState &tmp [temp0 15])
		(switch (= state newState)
			(0
				(poster init:)
				(addToPics doit:)
				(DoDisplay {And since you also play the role of Passionate Patti,}
					#at 10 20
					#color myTextColor4
					#font 2510
					#width 130
					#mode teJustCenter
				)
				(DoDisplay
					{you must use your extensive talents to help the F.B.I.}
					#at 10 62
					#color myTextColor4
					#font 2510
					#width 130
					#mode teJustCenter
				)
				(DoDisplay
					{expose corruption in the music industry...}
					#at 10 105
					#color myTextColor4
					#font 2510
					#width 130
					#mode teJustCenter
				)
				(= cycles 60)
			)
			(1
				(curRoom drawPic: 410)
				(arrow init:)
				(steam init:)
				(Patti
					init:
					setCycle: Walk
					setMotion: MoveTo 226 122 self
				)
				(Desmond init: cycleSpeed: 1 setCycle: Forward)
				(reflection init: doit:)
			)
			(2
				(Patti setMotion: MoveTo 157 125 self)
			)
			(3
				(DoDisplay
					{\1CSay Inspector, is that a Baretta in your pocket or are you just glad to see me?"}
					#at -1 5
					#color myTextColor5
					#font 2510
					#mode teJustCenter
				)
				(Desmond setLoop: 3 cycleSpeed: 0 setCycle: EndLoop self)
				(Patti setLoop: 3 stopUpd:)
				(reflection setLoop: 4)
			)
			(4
				(Desmond
					setLoop: 0
					setStep: 4 2
					setCycle: Walk
					setMotion: MoveTo 130 125 self
				)
			)
			(5
				(Desmond setLoop: 4 setCel: 0 setCycle: EndLoop)
				(= cycles 12)
			)
			(6
				(Desmond setCel: 0 setCycle: EndLoop)
				(= cycles 12)
			)
			(7
				(Desmond setCel: 0 setCycle: EndLoop)
				(= cycles 12)
			)
			(8
				(UnLoad PICTURE 114)
				(UnLoad PICTURE 410)
				(UnLoad PICTURE 100)
				(UnLoad VIEW 112)
				(UnLoad VIEW 410)
				(UnLoad VIEW 411)
				(UnLoad VIEW 412)
				(curRoom newRoom: 8)
			)
		)
	)
)

(instance Desmond of Actor
	(properties
		x 31
		y 123
		view 412
		loop 2
	)
)

(instance Patti of Actor
	(properties
		x 256
		y 107
		view 411
		signal fixedLoop
	)
)

(instance reflection of Actor
	(properties
		x 184
		y 116
		view 411
		loop 2
		cel 2
		priority 2
		signal fixPriOn
	)
	
	(method (doit)
		(= x (Patti x?))
		(= y (+ 125 (- 125 (Patti y?))))
		(= cel (Patti cel?))
		(super doit:)
	)
)

(instance steam of Prop
	(properties
		x 133
		y 162
		view 410
		cycleSpeed 3
	)
	
	(method (init)
		(super init:)
		(self setCycle: Oscillate)
	)
)

(instance arrow of Prop
	(properties
		x 286
		y 75
		view 410
		loop 1
		priority 10
		signal fixPriOn
		timer 2
	)
	
	(method (doit)
		(if (and timer (not (-- timer)))
			(if cel
				(= cel 0)
				(= timer 5)
			else
				(= cel 1)
				(= timer 2)
			)
		)
	)
	
	(method (dispose)
		(= timer 0)
		(super dispose:)
	)
)

(instance poster of PicView
	(properties
		x 220
		y 85
		view 112
	)
)
