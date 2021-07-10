;;; Sierra Script 1.0 - (do not remove this comment)
(script# 821)
(include game.sh)
(use Main)
(use Intrface)

(public
	EgoDead 0
)

(procedure (EgoDead theView theLoop theCel &tmp [str 80] deadView deadLoop deadCel)
	(switch gamePhase
		(1 ((ScriptID 371) dispose:))
		(2 ((ScriptID 372) dispose:))
		(3 ((ScriptID 374) dispose:))
	)
	(= deadView (if (>= argc 1) theView else 7))
	(= deadLoop (if (>= argc 2) theLoop else 0))
	(= deadCel (if (>= argc 3) theCel else 0))
	(if (> argc 3)
		(Format @str &rest)
	else
		(Format @str 821 0)
	)
	(globalSound number: (SoundFX 71) loop: 1 play:)
	(repeat
		(switch
			(Print @str
				#icon deadView deadLoop deadCel
				#mode teJustCenter
				#title {Restart? Restore?... At least once more!!!}
				#button {Restore} 1
				#button {Restart} 2
				#button {____Quit____} 3
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
