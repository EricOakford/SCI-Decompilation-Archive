;;; Sierra Script 1.0 - (do not remove this comment)
(script# QSCRIPT)
(include game.sh)

;;;(procedure
;;;	QueScript
;;;	ScriptObject	
;;;)

(public 
	QueScript	0
)

(procedure (QueScript theClient theScript optCaller optReg
		&tmp theObj theCaller theReg aTmp)
	
	;;	Author: Pablo Ghenis, June 1989
	;;
	;;	Usage:
	;;	
	;;	(QueScript anObj scriptToChainTo optionalWhoToCallWhenDone optRegister)
	;;	
	;;	this will trigger the following when anObj is done executing its current
	;;	script if any:
	;;	(anObj setScript scriptToChainTo optionalWhoToCallWhenDone optRegister)
	;;	
	;;	scriptToChainTo can be the number of a module which has a real script
	;;	as public entry zero. This mechanism provides an easy route for 
	;;	"overlaying" portions of long or mutually exclusive scripts to save
	;;	memory.
	;;	
	;;	IMPORTANT! Queued scripts MUST end in a (self dispose:), NOT a
	;;	(client setScript: NULL). These two statements used to be equivalent
	;;	but are no more.
	
	(= theObj theClient)
	(= theCaller	0)	;default
	(= theReg		0)	;default
	
	(if (>= argc 3)
		(= theCaller optCaller)
		
		(if (>= argc 4)
			(= theReg optReg)
		)
		
		;;for 3 or 4 args we must load in order to set properties
		((= theScript (ScriptObject theScript))
			caller:		theCaller
			,register:	theReg
		)
	)
	
	(while TRUE
	
		(cond
			((not theObj)	;oops! went too far
				;;caller and register set above if specified
				(theClient setScript:(ScriptObject theScript))
				(break)
			)
			((not (theObj respondsTo: #next))
				(if (theObj script?)
					(= theObj (theObj script?))	;follow link
				else
					;;caller and register set above if specified
					(theObj setScript:(ScriptObject theScript))
					(break)
				)
			)
			((== theObj theClient)
				(= theObj (theClient script?))
				(if (== theObj (theObj script?))
					(= theObj (theObj new:))
				)
			)
			((theObj next?)
				;;following a next-link requires immediate load
				(= aTmp (ScriptObject (theObj next?)))
				(theObj next: 
					(cond
						((== theObj (theObj next?))
							((aTmp new:) next: NULL yourself:)
						)
						((& (theObj -info-?) CLONED)
							((aTmp new:) next: NULL yourself:)
						)
						(else
							aTmp
						)
					)
				)
				(= theObj (theObj next:))	;follow the link
			)
			(else
				(theObj next: 
					(if (or
							(== theScript theObj)
							(and
								(IsObject theScript)
								(& (theScript -info-?) CLONED)
							)
						)
						(theScript new:)
					else 
						theScript
					)
				)
				(break)
			)
		);cond
		
	);while
); QueScript

(procedure (ScriptObject x)
	(if (IsObject x) x else (ScriptID x))
)
