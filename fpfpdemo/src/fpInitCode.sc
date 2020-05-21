;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPINIT) ;14
(include game.sh)
(use Main)
(use Print)
(use Talker)
(use Window)
(use System)

(public
	fpInitCode 0
)

(instance fpInitCode of Code
	(properties)
	
	(method (init &tmp versionFile)
		(= score 0)
		(= possibleScore 50)
		(= userFont 69)
		(= bigFont 2108)
		(= smallFont 1207)
		(= global116 2407)
		(= global117 4115)
		(= global118 2510)
		(= scoreFont 30)
		(Print font: userFont)
		((= narrator Narrator)
			font: userFont
			back: 34
			keepWindow: TRUE
		)
		(= msgType TEXT_MSG)
		(= systemWindow (ScriptID 0 10))
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileFGets buildDate 20 versionFile)
		(FileIO fileFGets usPhone 20 versionFile)
		(FileIO fileFGets intPhone 20 versionFile)
		(FileIO fileClose versionFile)
		(= useSortedFeatures TRUE)
		(= eatMice 30)
		(StrCpy @sysLogPath {})
		(theGame setCursor: theCursor 1 304 172 detailLevel: 5)
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
		(systemWindow color: 0 back: 34)
		(SysWindow back: 34)
		((ScriptID FPINV 1)
			color: 0
			back: 17
			topBordColor: 17
			lftBordColor: 17
			rgtBordColor: 17
			botBordColor: 17
			insideColor: 19
			topBordColor2: 18
			lftBordColor2: 18
			botBordColor2: 20
			rgtBordColor2: 20
		)
	)
)
