;;; Sierra Script 1.0 - (do not remove this comment)
(script# STARTAROOM) ;896
(include game.sh)
(use Main)
(use Procs)
(use LoadMany)
(use System)

(public
	StartARoom 0
)

(procedure (StartARoom nr &tmp index obj event [temp3 20])
	(while ((= event (Event new:)) type?)
		(event dispose:)
	)
	(event dispose:)
	(if (OneOf nr NOTICE NOTICE2 INTRO SPEED ENDGAME2 CHARSAVE)
		(theIconBar disable:)
	else
		(theIconBar enable:)
	)
	(&= spellMask
		(| SPELL_OPEN SPELL_DETECT SPELL_TRIGGER SPELL_DAZZLE
			SPELL_ZAP SPELL_CALM SPELL_FLAMEDART SPELL_FETCH
		)
	)		 
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(mouseDownHandler release:)
	(keyDownHandler release:)
	(directionHandler release:)
	(if (== nr ENDGAME)
		(Bclr fInMainGame)
		(FixTime 12 0)
		(= Night FALSE)
		(= currentPalette 0)
	)
	(if (and
			(not monsterNum)
			(!= daggerRoom 73)	;to keep the daggers on the target
		)
		(= hitDaggers 0)
	)
	(for ((= index 0)) (< index iLastInvItem) ((++ index))
		((inventory at: index) amtDropped: 0)
	)
	((ScriptID GLORYINV 2) release: init:)
	(LoadMany FALSE
		EXTRA JUMP CHASE DOOR PRINTD TELLER WARE STATUSBAR
		SLEEP REST TIME CASTCALM CASTOPEN CASTDAZZ CASTDART
		TALKOBJ TARGET REVERSE FOLLOW WANDER PRAGFAIL EGOSEZ
	)
	(DisposeScript STARTAROOM)
)

(instance dumpMeMaybe of Code
	(method (doit nr)
		(if (nr respondsTo: #dumpIt)
			((ScriptID GLORYINV 2) delete: nr)
		)
	)
)
