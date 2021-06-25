;;; Sierra Script 1.0 - (do not remove this comment)
(script# LBINIT)
(include game.sh)
(use Main)
(use ColorInit)
(use Print)
(use Talker)
(use System)

(public
	lb2InitCode 0
)

(instance lb2InitCode of Code
	
	(method (init &tmp versionFile)
		(= possibleScore 1000)
		(= userFont 69)
		(= bigFont 2108)
		(= smallFont 1207)
		(= global118 2407)
		(= global119 4115)
		(= global120 2510)
		(Print font: userFont)
		((= narrator Narrator)
			font: userFont
			back: 15
			keepWindow: TRUE
		)
		(= msgType TEXT_MSG)
		(= systemWindow (ScriptID 0 9))
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileFGets usPhone 20 versionFile)
		(FileIO fileFGets intPhone 20 versionFile)
		(FileIO fileFGets hintPhone 20 versionFile)
		(FileIO fileClose versionFile)
		(ColorInit)
		(= useSortedFeatures TRUE)
		(= eatMice 30)
		(StrCpy @sysLogPath {})
		(theGame
			setCursor: theCursor TRUE 304 172
			detailLevel: 5
		)
		(= textSpeed 2)
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
		((ScriptID 15 1)
			color: 0
			back: myBackColor
			topBordColor: myBackColor
			lftBordColor: myBackColor
			rgtBordColor: myBackColor
			botBordColor: myBackColor
			insideColor: myInsideColor
			topBordColor2: myTopBordColor
			lftBordColor2: myTopBordColor
			botBordColor2: myBotBordColor
			rgtBordColor2: myBotBordColor
		)
		((ScriptID 21 0) doit: 257)
		((ScriptID 21 0) doit: 258)
		((ScriptID 21 0) doit: 259)
		((ScriptID 21 0) doit: 260)
		((ScriptID 21 0) doit: 261)
		((ScriptID 21 0) doit: 262)
		((ScriptID 21 0) doit: 273)
		((ScriptID 21 0) doit: 513)
		((ScriptID 21 0) doit: 514)
		((ScriptID 21 0) doit: 515)
		((ScriptID 21 0) doit: 516)
		((ScriptID 21 0) doit: 517)
		((ScriptID 21 0) doit: 518)
		((ScriptID 21 0) doit: 519)
		((ScriptID 21 0) doit: 771)
		((ScriptID 21 0) doit: 1026)
		((ScriptID 21 0) doit: 1027)
		((ScriptID 21 0) doit: 1028)
		(DisposeScript 21)
		(DisposeScript 12)
	)
)
