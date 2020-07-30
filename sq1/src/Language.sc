;;; Sierra Script 1.0 - (do not remove this comment)
(script# 932)
(include game.sh)
(use Main)
(use Intrface)

(public
	proc932_0 0
	proc932_1 1
	proc932_2 2
	RestoreSubLang 3
	SaveSubLang 4
	proc932_5 5
	proc932_6 6
	proc932_7 7
)

(local
	theGGameSubtitleLang
	local1
)
(procedure (proc932_0)
	(localproc_003c 0 &rest)
)

(procedure (proc932_1)
	(localproc_003c 1 &rest)
)

(procedure (proc932_2 param1 param2 param3 param4)
	(return
		(cond 
			((== (theGame printLang?) ENGLISH)
				(if
				(or (< argc 3) (== (theGame subtitleLang?) NULL))
					param1
				else
					param3
				)
			)
			(
			(or (< argc 4) (== (theGame subtitleLang?) NULL)) param2)
			(else param4)
		)
	)
)

(procedure (RestoreSubLang &tmp theGameSubtitleLang)
	(if
		(and
			(not theGGameSubtitleLang)
			(= theGameSubtitleLang (theGame subtitleLang?))
		)
		(= theGGameSubtitleLang theGameSubtitleLang)
		(theGame subtitleLang: NULL)
	)
	(return theGameSubtitleLang)
)

(procedure (SaveSubLang &tmp oldLang)
	(if
		(and
			(= oldLang theGGameSubtitleLang)
			(not (theGame subtitleLang?))
		)
		(theGame subtitleLang: theGGameSubtitleLang)
		(= theGGameSubtitleLang NULL)
	)
	(return oldLang)
)

(procedure (proc932_5 &tmp theGameSubtitleLang)
	(return
		(if (= theGameSubtitleLang (theGame subtitleLang?))
			(theGame subtitleLang: (theGame printLang?))
			(theGame printLang: theGameSubtitleLang)
			(return TRUE)
		else
			FALSE
		)
	)
)

(procedure (proc932_6 param1 param2 param3 param4 &tmp theGamePrintLang theGameSubtitleLang temp2 [temp3 1000])
	(if (== argc 4)
		(GetFarText @temp3 param3 param4)
	else
		(StrCpy @temp3 param3)
	)
	(= theGamePrintLang (theGame printLang?))
	(= theGameSubtitleLang (theGame subtitleLang?))
	(theGame printLang: ENGLISH subtitleLang: NULL)
	(kernel_123 param1 @temp3 0)
	(if (= temp2 0)
		(theGame printLang: temp2)
		(kernel_123 param2 @temp3 0)
	else
		(StrCpy param2 {})
	)
	(theGame
		printLang: theGamePrintLang
		subtitleLang: theGameSubtitleLang
	)
	(return param1)
)

(procedure (proc932_7 param1 param2)
	(return (if (== (theGame parseLang?) ENGLISH) param1 else param2))
)

(procedure (localproc_003c param1 &tmp theGamePrintLang theGameSubtitleLang)
	(= theGameSubtitleLang (theGame subtitleLang?))
	(theGame subtitleLang: NULL)
	(if param1 (Display &rest) else (Print &rest #first))
	(if theGameSubtitleLang
		(= theGamePrintLang (theGame printLang?))
		(theGame printLang: theGameSubtitleLang)
		(if param1 (Display &rest) else (Print &rest))
		(theGame printLang: theGamePrintLang)
	)
	(theGame subtitleLang: theGameSubtitleLang)
)
