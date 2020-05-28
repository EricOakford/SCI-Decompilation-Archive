;;; Sierra Script 1.0 - (do not remove this comment)
(script# 150)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm150 0
)

(local
	saveBits
	local1
	[str 200]
)
(instance rm150 of Room
	(properties
		picture 888
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: demoRm150Scr)
	)
)

(instance demoRm150Scr of Script
	(properties)
	
	(method (doit)
		(if (== state 2)
			(Palette PALIntensity 1 255 (* (- 50 cycles) 2))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(Load RES_VIEW 424)
				(theMusic number: 430 setLoop: -1 play:)
				(= ticks 65)
			)
			(2
				(Palette PALIntensity 1 255 0)
				(= cycles 50)
				((View new:)
					view: 424
					signal: ignrAct
					loop: 0
					cel: 0
					x: 101
					y: 33
					priority: 0
					init:
				)
				((Actor new:)
					view: 424
					signal: (| ignrAct fixedLoop fixPriOn)
					loop: 1
					cel: 0
					x: 171
					y: 209
					yStep: 2
					priority: 12
					moveSpeed: 10
					setMotion: MoveTo 171 162
					init:
				)
				((Actor new:)
					view: 424
					signal: (| ignrAct fixedLoop fixPriOn)
					loop: 2
					cel: 0
					x: 170
					y: 172
					priority: 14
					yStep: 2
					moveSpeed: 10
					setCycle: Forward
					setMotion: MoveTo 170 125
					init:
				)
				((Actor new:)
					view: 424
					signal: (| ignrAct fixedLoop fixPriOn)
					loop: 2
					cel: 2
					x: 189
					y: 63
					scaleSignal: 1
					scaleX: 48
					scaleY: 48
					yStep: 1
					moveSpeed: 18
					setCycle: Forward
					setMotion: MoveTo 189 50
					init:
				)
				((Actor new:)
					view: 424
					signal: (| ignrAct fixedLoop fixPriOn)
					loop: 2
					cel: 2
					x: 122
					y: 63
					scaleSignal: 1
					scaleX: 48
					scaleY: 48
					yStep: 1
					moveSpeed: 18
					setCycle: Forward
					setMotion: MoveTo 122 53
					init:
				)
				((Prop new:)
					view: 424
					signal: (| ignrAct fixPriOn)
					priority: 1
					loop: 3
					cel: 0
					x: 154
					y: 137
					cycleSpeed: 50
					setCycle: EndLoop
					init:
				)
				(= local1
					((View new:)
						view: 424
						signal: (| ignrAct fixPriOn)
						loop: 4
						cel: 0
						posn: 89 163
						priority: 15
						init:
						yourself:
					)
				)
			)
			(3
				(local1 dispose:)
				(theText init:)
				(= ticks 180)
			)
			(4
				(theText dispose:)
				(= ticks 80)
			)
			(5
				(theText cel: 1 init:)
				(= ticks 180)
			)
			(6
				(theMusic fade:)
				(theText dispose:)
				(= ticks 80)
			)
			(7
				(Palette PALIntensity 1 255 100)
				(cast eachElementDo: #dispose)
				(= ticks 30)
			)
			(8
				(self setScript: logoScr self)
			)
			(9 (curRoom newRoom: 100))
		)
	)
)

(instance logoScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ecoTitle init:)
				(= ticks 120)
			)
			(1
				(Message MsgGet 105 0 0 10 1 @str)
				(= saveBits
					(Display @str
						p_mode teJustCenter
						p_at 0 170
						p_font SYSFONT
						p_color 8
						p_width 320
						p_save
					)
				)
				(= ticks 240)
			)
			(2
				(Display {} p_restore saveBits)
				(= ticks 60)
			)
			(3
				(ecoTitle dispose:)
				(= cycles 2)
			)
			(4 (self dispose:))
		)
	)
)

(instance ecoTitle of View
	(properties
		x 50
		y 50
		view 92
		signal stopUpdOn
	)
)

(instance theText of View
	(properties
		y 172
		view 93
		loop 4
		priority 15
		signal fixPriOn
	)
	
	(method (init)
		(= x (- (/ (- 320 (CelWide view loop cel)) 2) 5))
		(super init: &rest)
	)
)
