;;; Sierra Script 1.0 - (do not remove this comment)
(script# 822)
(include game.sh)

(public
	ChangeScriptState 0
)

(procedure (ChangeScriptState obj)
	(if (obj script?)
		(ChangeScriptState (obj script?) &rest)
	else
		(obj setScript: &rest)
	)
)
