;;; Sierra Script 1.0 - (do not remove this comment)
(script# LL5INIT)
(include game.sh)
(use Main)
(use ColorInit)
(use System)

(public
	ll5InitCode 0
)

(instance ll5InitCode of Code

	(method (init &tmp temp0)
		(= systemWindow (ScriptID 0 21))
		(ColorInit)
		(= useSortedFeatures TRUE)
		(= eatMice 30)
		(StrCpy @sysLogPath {})
		(theGame
			egoMoveSpeed: 6
			setCursor: theCursor TRUE 304 172
			detailLevel: 5
		)
		(= waitCursor HAND_CURSOR)
		(= possibleScore 1000)
		(= userFont 2107)
		(= bigFont 2108)
		(= smallFont 1207)
		(= resumeFont 2407)
		(= giantFont 4115)
		(= giantFont2 2510)
		(= textSpeed 12)
		(= numVoices (DoSound NumVoices))
		(if
			(and
				(>= (= numColors (Graph GDetect)) 2)
				(<= numColors 16)
			)
			(Bclr fIsVGA)
		else
			(Bset fIsVGA)
		)
		(systemWindow color: 0 back: myBackColor)
		((ScriptID 19 1)
			color: 0
			back: myBackColor
			topBordColor: myBackColor
			lftBordColor: myBackColor
			rgtBordColor: myBackColor
			botBordColor: myBackColor
			insideColor: myInsideColor
			topBordColor2: myBordColor
			lftBordColor2: myBordColor
			botBordColor2: myLowlightColor
			rgtBordColor2: myLowlightColor
		)
		((ScriptID 0 20) color: 0 back: myBackColor)
		((ScriptID 19 0) init:)
		(UnLoad SCRIPT COLORINIT)
	)
)
