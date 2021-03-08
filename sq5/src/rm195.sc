;;; Sierra Script 1.0 - (do not remove this comment)
(script# 195)
(include game.sh)
(use Main)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm195 0
)

(local
	[rogX 18] = [176 171 165 157 148 137 127 115 102 86 72 60 51 38 26 12 -1 -14]
	[rogY 18] = [126 128 130 132 134 136 138 140 142 144 146 148 150 152 154 156 158 160]
	[rogscale 18] = [10 12 14 17 21 26 34 42 52 69 83 94 103 119 128 128 128 128]
	i
)
(instance rm195 of Room
	(properties
		picture 35
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(self setRegions: rgStarCon)
		(LoadMany RES_VIEW 155)
		(NormalEgo 0)
		(super init:)
		(curRoom setScript: sByeByeRog)
	)
)

(instance sByeByeRog of Script
	
	(method (doit)
		(Palette PALCycle 15 22 1)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ejectRoger
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 0
					setMotion: MoveTo 180 124 self
				)
			)
			(1
				(theMusic2 number: 154 setLoop: 1 play:)
				(= ticks 5)
			)
			(2
				(ejectRoger
					setCycle: 0
					x: [rogX i]
					y: [rogY i]
					scaleX: [rogscale i]
					scaleY: [rogscale i]
				)
				(if (<= (++ i) 17) (-- state))
				(= ticks 5)
			)
			(3
				(cond 
					((not (Btst fAttendedClass))
						(EgoDead deathSLACKER)
					)
					((< testScore 4)
						(EgoDead deathTESTFAIL)
					)
					(else
						(EgoDead deathCHEATER)
					)
				)
			)
		)
	)
)

(instance ejectRoger of Actor
	(properties
		x 180
		y 103
		view 155
		scaleSignal scalable
		scaleX 7
		scaleY 7
		moveSpeed 3
	)
)
