;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEATH)
(include game.sh) (include "99.shm")
(use Main)
(use LBRoom)
(use Print)

(public
	deathRoom 0
)

(instance deathRoom of LBRoom
	(properties
		picture 780
		horizon 120
		north 350
	)
	
	(method (init &tmp deadMsg theY)
		(super init:)
		(theMusic number: 99 flags: mNOPAUSE loop: 1 play:)
		(if (== deathReason 15)
			(= theY 86)
		else
			(= theY 62)
		)
		(= deadMsg (+ deathReason 1))
		(Animate (cast elements?) FALSE)
		(repeat
			(switch
				(Print
					addText: N_DEATH V_DIE deadMsg 0 100 0
					addIcon: 99 0 deathReason 0 0
					addButton: 1 N_BUTTON NULL NULL 1 0 theY DEATH
					addButton: 2 N_BUTTON NULL NULL 2 (WhichLanguage 70 70 62 70 70) theY DEATH
					addButton: 3 N_BUTTON NULL NULL 3 140 theY DEATH
					init:
				)
				(1
					(theGame restore:)
				)
				(2
					(theGame restart:)
				)
				(3
					(= quit TRUE)
					(break)
				)
			)
		)
	)
)
