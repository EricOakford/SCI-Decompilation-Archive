;;; Sierra Script 1.0 - (do not remove this comment)
(script# GKINIT)
(include game.sh)
(use Main)
(use Procs)
(use GKEgo)
(use BordWind)
(use User)
(use System)

(public
	gKInitCode 0
)

(instance gKInitCode of Code
	
	(method (doit &tmp versionFile [str 8])
		(= userFont 4)
		(User alterEgo: GKEgo canControl: FALSE canInput: FALSE)
		(= useSortedFeatures TRUE)
		(= systemWindow BorderWindow)
		(= msgType TEXT_MSG)
		(= possibleScore (if (not isDemo) 500 else 25))
		(= score 0)
		(systemWindow
			topBordColor: 5
			lftBordColor: 5
			rgtBordColor: 4
			botBordColor: 3
			color: 26
			back: 48
		)
		(if
			(and
				(>= (= numColors (Graph GDetect)) 2)
				(<= numColors 16)
			)
			(Bclr fIsVGA)
		else
			(Bset fIsVGA)
		)
		(= numVoices (DoSound NumVoices))
		(Format @str 10 0 99)
		(if (FileIO fileExists @str)
			(= debugging TRUE)
		else
			(= debugging FALSE)
		)
		(= eatMice 2)
		(= version {x.yyy.zzz})
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileClose versionFile)
		(DisposeScript GKINIT)
	)
)
