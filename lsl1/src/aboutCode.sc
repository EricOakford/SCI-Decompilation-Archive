;;; Sierra Script 1.0 - (do not remove this comment)
(script# ABOUT) ;811
(include game.sh)
(use Main)
(use Intrface)
(use System)

(public
	aboutCode 0
)

(local
	local0
)
(instance aboutCode of Code
	(properties)
	
	(method (doit &tmp [str 200])
		((ScriptID 0 24)
			view: 856
			loop: 0
			cycleSpeed: (* (+ howFast 1) 4)
		)
		(Print 811 0
			#font smallFont
			#mode 1
			#width 200
			#title {Sierra Presents}
		)
		(Print 811 1
			#font smallFont
			#mode 1
			#icon (ScriptID 0 24)
			#width 200
			#title
			{Sierra Presents}
		)
		(Print 811 2
			#font smallFont
			#mode 1
			#width 200
			#title {Sierra Presents}
		)
		(Print 811 3
			#font smallFont
			#mode 1
			#width 200
			#title {Sierra Presents}
		)
		(Print
			(Format @str 811 4 version)
			#font smallFont
			#mode 1
			#width 222
			#title {Sierra Presents}
		)
		(Print 811 5
			#font smallFont
			#mode 1
			#width 222
			#title {A Paid Commercial Announcement}
		)
		(Print
			(cond 
				((GameHour)
					(Format @str 811 6
						(GameHour) (if (== (GameHour) 1) {} else {s})
						(GameMinutes) (if (== (GameMinutes) 1) {} else {s})
						(GameSeconds) (if (== (GameSeconds) 1) {} else {s})
						score (if (== score 1) {} else {s})
					)
				)
				(score
					(Format @str 811 7
						(GameMinutes) (if (== (GameMinutes) 1) {} else {s})
						(GameSeconds) (if (== (GameSeconds) 1) {} else {s})
						score (if (== score 1) {} else {s})
					)
				)
				(else
					(Format @str 811 8
						(GameMinutes) (if (== (GameMinutes) 1) {} else {s})
						(GameSeconds) (if (== (GameSeconds) 1) {} else {s})
					)
				)
			)
			#font smallFont
			#title {You've Only Just Begun}
		)
		(Print 811 9 #font smallFont)
	)
)
