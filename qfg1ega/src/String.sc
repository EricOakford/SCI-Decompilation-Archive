;;; Sierra Script 1.0 - (do not remove this comment)
(script# STRING) ;802
(include game.sh)
(use User)

(public
	ToLower 0
	StrFind 1
	LookFor 2
)

(procedure (ToLower aChar)
	(return
		(if (or (< aChar 65) (> aChar 90))
			(return aChar)
		else
			(return (+ (- aChar 65) 97))
		)
	)
)

(procedure (StrFind theString theWord &tmp len wordLen mainPtr wordPtr)
	(= wordLen (StrLen theWord))
	(= len (- (= len (StrLen theString)) wordLen))
	(while (>= len 0)
		(= wordPtr 0)
		(= mainPtr len)
		(while (< wordPtr wordLen)
			(if
				(!=
					(ToLower (StrAt theWord wordPtr))
					(ToLower (StrAt theString mainPtr))
				)
				(break)
			)
			(++ wordPtr)
			(++ mainPtr)
		)
		(if (== wordPtr wordLen) (return (+ wordPtr 1)))
		(-- len)
	)
	(return FALSE)
)

(procedure (LookFor event parseIt &tmp index inLine)
	(if
		(and
			(User canInput?)
			(not (event claimed?))
			(== (event type?) keyDown)
			(or
				(== (event message?) (User echo?))
				(and
					(<= SPACEBAR (event message?))
					(<= (event message?) 127)
				)
			)
		)
		(event claimed: TRUE)
		(if (User getInput: event)
			(= inLine (User inputLineAddr?))
			(= index 1)
			(while (< index argc)
				(if
				(StrFind inLine [parseIt (- index 1)])
					(return index)
				)
				(++ index)
			)
			(event type: allEvents)
			(event claimed: FALSE)
			(Parse inLine event)
			(User said: event)
		)
	)
	(return FALSE)
)
