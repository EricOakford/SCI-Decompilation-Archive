;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include game.sh)
(use Main)
(use Osc)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom8 0
)

(local
	eyeTimer
)
(instance demoRoom8 of Room
	(properties
		picture 795
		style HSHUTTER
	)
	
	(method (init)
		(LoadMany PICTURE 795 100)
		(LoadMany VIEW 795)
		(Load SOUND 5)
		(super init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	(method (changeState newState &tmp [temp0 15])
		(switch (= state newState)
			(0
				(nose init: setCycle: Forward)
				(= eyeTimer 20)
				(leftEye init:)
				(rightEye init:)
				(mouth init:)
				(DoDisplay {Remember Al Lowe's slogan:}
					#at -1 171
					#color colYellow
					#font 2510
					#mode teJustCenter
				)
				(= cycles 20)
			)
			(1
				(if (or (rightEye cel?) (leftEye cel?))
					(-- state)
					(= cycles 1)
				else
					(DrawPic 795)
					(AlVoice play:)
					(DoDisplay {"BETTER BABES THRU TECHNOLOGY!"}
						#at -1 171
						#color colWhite
						#font 2510
						#mode teJustCenter
					)
					(= cycles 20)
				)
			)
			(2
				(if (or (rightEye cel?) (leftEye cel?))
					(-- state)
					(= cycles 1)
				else
					(DrawPic 795)
					(mouth setCycle: EndLoop self)
				)
			)
			(3
				(mouth dispose:)
				(= cycles 5)
			)
			(4
				(= eyeTimer 20)
				(= cycles 20)
			)
			(5
				(DoDisplay {Leisure Suit Larry 5 is available}
					#at -1 161
					#color colYellow
					#font 2510
					#mode teJustCenter
				)
				(DoDisplay {at swinging software stores everywhere!}
					#at -1 172
					#color colYellow
					#font 2510
					#mode teJustCenter
				)
				(= cycles 40)
			)
			(6
				(cast eachElementDo: #hide eachElementDo: #dispose)
				(curRoom drawPic: 100 FADEOUT)
				(theMusic fade:)
				(= cycles 20)
			)
			(7
				(UnLoad PICTURE 795)
				(UnLoad PICTURE 100)
				(UnLoad VIEW 795)
				(curRoom newRoom: 1)
			)
		)
	)
)

(instance AlVoice of Sound
	(properties
		number 5
	)
)

(instance leftEye of Prop
	(properties
		x 165
		y 60
		view 795
	)
)

(instance rightEye of Prop
	(properties
		x 140
		y 56
		view 795
		loop 2
	)
	
	(method (doit)
		(super doit:)
		(if (and eyeTimer (not (-- eyeTimer)))
			(self setCycle: Oscillate 1)
			(leftEye setCycle: Oscillate 1)
			(= eyeTimer (Random 7 15))
		)
	)
)

(instance nose of Prop
	(properties
		x 149
		y 71
		view 795
		loop 1
		cycleSpeed 8
	)
)

(instance mouth of Prop
	(properties
		x 149
		y 77
		view 795
		loop 3
		cycleSpeed 4
	)
)
