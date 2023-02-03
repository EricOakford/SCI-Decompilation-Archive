;;; Sierra Script 1.0 - (do not remove this comment)
(script# LASTLINK)

;;;(procedure
;;;	LastLink
;;;;	LastScript
;;;;	LastClient
;;;)

(public
	LastLink			0
;	LastScript		1
;	LastClient		2
)

(procedure (LastLink whatLink whose &tmp linkValue)

	;; Author: Pablo Ghenis, 8/7/89
	;;
	;; Usage:
	;;			(LastClient self) or (LastLink #client aScript) 
	;;			(LastScript self) or (LastLink #script anActor)
	;;			etc...
	;;
	;;	CAUTION! #aProperty will NOT evaluate to the selector number
	;;	if contained in a method of an object that has aProperty as
	;;	one of its properties. For such cases, buffer procedures like
	;;	LastScript and LastClient are required. This is obviously the case
	;;	whenever the second argument is 'self'

	(if (and 
			(whose respondsTo:whatLink) 
			(= linkValue (whose whatLink?))
		) 
		(LastLink  whatLink linkValue)
	else
		whose
	)
)
;(procedure (LastScript whose)
;	(LastLink #script whose)
;)
;(procedure (LastClient whose)
;	(LastLink #client whose)
;)
