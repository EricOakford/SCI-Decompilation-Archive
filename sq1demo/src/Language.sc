;;; Sierra Script 1.0 - (do not remove this comment)
(script# LANGUAGE)
(include game.sh)
(include language.sh)
(use Main)
(use Intrface)

(local
	savedSubLang = 0
	savedSubLangLevel = 0
)

;;;(procedure
;;;	PrintSplit	
;;;	DisplaySplit
;;;	LangSwitch
;;;	SaveSubLang
;;;	RestoreSubLang
;;;	SwapLangs
;;;	ShowSplit
;;;	StrSplitInTwo
;;;	IfEnglishElse
;;;)

(public
	PrintSplit		0
	DisplaySplit	1
	LangSwitch		2
	SaveSubLang		3
	RestoreSubLang	4
	SwapLangs		5
	StrSplitInTwo	6
	IfEnglishElse	7
)

(enum
	PRINT
	DISPLAY
)

(procedure (PrintSplit)
	(ShowSplit FALSE &rest)
)

(procedure (DisplaySplit)
	(ShowSplit TRUE &rest)
)

(procedure (ShowSplit funIsDisplay &tmp mainLang subLang)
	;;
	;;Split a large bilingual Display into a separate one for each language
	;;
	(= subLang (theGame subtitleLang?))
	(theGame subtitleLang: NULL)
	(if funIsDisplay (Display &rest) else (Print &rest #first))
	(if subLang
		(= mainLang (theGame printLang?))
		(theGame printLang: subLang)
		(if funIsDisplay (Display &rest) else (Print &rest))
		(theGame printLang: mainLang)
	)
	(theGame subtitleLang: subLang)
)

(procedure (LangSwitch EngVal ForVal EngForVal ForEngVal)
	(if (== (theGame printLang?) ENGLISH)
		(if (or (< argc 3) (== (theGame subtitleLang?) NULL))
			EngVal
		else 
			EngForVal
		)
	else
		(if (or (< argc 4) (== (theGame subtitleLang?) NULL))
			ForVal
		else 
			ForEngVal
		)
	)
)

(procedure (SaveSubLang &tmp subL)
	(if (and
			(not savedSubLang)
			(= subL (theGame subtitleLang?))
		);and
		
		(= savedSubLang subL)
		(theGame subtitleLang: NULL)
	)
	(return subL)
)

(procedure (RestoreSubLang &tmp subL)
	(if (and
			(= subL savedSubLang)
			(not (theGame subtitleLang?))
		)
		(theGame subtitleLang: savedSubLang)
		(= savedSubLang NULL)
	)
	(return subL)
)

(procedure (SwapLangs &tmp subL)
	(if (= subL (theGame subtitleLang?))
		(theGame subtitleLang: (theGame printLang?))
		(theGame printLang: subL)
		(return TRUE)
	;;else
	;;	(return FALSE)
	)
)

(procedure (StrSplitInTwo engBuf forBuf bufOrNum optEntry 
		&tmp mainL subL altLang [thisBuf 1000])
	
	(if (== argc 4)
		(GetFarText @thisBuf bufOrNum optEntry)
	else
		(StrCpy @thisBuf bufOrNum)
	)
 	
 	(= mainL (theGame printLang?))
	(= subL (theGame subtitleLang?))
	(theGame printLang: ENGLISH subtitleLang: NULL)
	
	(StrSplit engBuf @thisBuf NULL)
	
;	(= altLang (if (!= mainL ENGLISH) mainL else subL))
	(= altLang ALTLANG)
	(if altLang
		(theGame printLang: altLang)
		(StrSplit forBuf @thisBuf NULL)
	else
		(StrCpy forBuf {})
	)
	
	(theGame printLang: mainL, subtitleLang: subL)
	(return engBuf)
)

(procedure (IfEnglishElse EngVal ForVal)
	(if (== (theGame parseLang?) ENGLISH) EngVal else ForVal)
)