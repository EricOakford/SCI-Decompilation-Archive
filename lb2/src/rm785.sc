;;; Sierra Script 1.0 - (do not remove this comment)
(script# 785)
(include game.sh)
(use Main)
(use LBRoom)
(use Actor)
(use System)

(public
	rm785 0
)

(local
	theCase
	theNoun
	local2
	local3 = [342 597 850 1113 1366 1628 1885 2131 2394 2651 2900 3166 3424 3683 3937 4184 4439 4688 4962]
)
(instance rm785 of LBRoom
	(properties
		picture 780
	)
	
	(method (init)
		(super init:)
		(theMusic number: 140 flags: mNOPAUSE loop: -1 play:)
		(= theCase
			(switch global126
				(1 1)
				(2 2)
				(3 3)
				(4 4)
			))
		(theIconBar disable:)
		(theGame setCursor: 996)
		(= theNoun (>> (& [local3 local2] $ff00) $0008))
		(characterView
			view: (+ 1800 (& [local3 local2] $00ff))
			loop: 1
			x: 10
			y: 10
			init:
		)
		(narrator x: 10 y: 113 talkWidth: 290)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom
					drawPic: (if (== (characterView view?) 1898) 785 else 780) 10
				)
				(= cycles 2)
			)
			(1
				(messager say: theNoun NULL theCase 0 self)
			)
			(2 (++ local2) (= ticks 120))
			(3
				(if (== local2 19)
					(theMusic fade: 0 12 30 1)
					(curRoom
						newRoom: (if (OneOf global126 1 4) 780 else 790)
					)
				else
					(= theNoun (>> (& [local3 local2] $ff00) $0008))
					(characterView
						view: (+ 1800 (& [local3 local2] $00ff))
						loop: 1
						cel: 0
						setPri: 15
						x:
						(switch (mod theNoun 3)
							(0 220)
							(1 10)
							(2 115)
						)
						y:
							(cond 
								((== theNoun 19) 5)
								(
								(and (< (mod theNoun 6) 4) (> (mod theNoun 6) 0)) 10)
								(else 103)
							)
						init:
					)
					(narrator
						x: (if (== theNoun 19) 160 else 10)
						y: (if (== (characterView y?) 10) 113 else 10)
						talkWidth: (if (== theNoun 19) 140 else 290)
					)
					(self changeState: 0)
				)
			)
		)
	)
)

(instance characterView of View)
