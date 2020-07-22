;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include game.sh)
(use Main)
(use Procs)
(use GControl)
(use IconBar)


(class PuzzleIcon of IconItem
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state $0000
		cursor -1
		type $4000
		message -1
		modifiers $0000
		signal $0001
		helpStr 0
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		client 0
	)
)

(class PuzzleBar of GameControls
	(properties
		elements 0
		size 0
		height 200
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		port 0
		window 0
		state $0000
		activateHeight 0
		y 0
		okButton 0
		iconHeight 22
		puzzleHeight 10
		bottomHeight 22
		eventX 0
		eventY 0
		hintFlag 0
		solvedFlag 0
		script 0
		inited 0
		shown 0
	)
	
	(method (init &tmp temp0)
		(if (not inited)
			(= inited 1)
			(if
			(< (= temp0 (- (window right?) (window left?))) 90)
				(= temp0 90)
			)
			(self
				add:
					(puzzleCoin new:)
					((puzzleOK new:) nsLeft: (/ (- temp0 40) 2) yourself:)
					((puzzleHelp new:) nsLeft: (- temp0 31) yourself:)
			)
			(super init: &rest)
		)
	)
	
	(method (dispose)
		(= inited 0)
		(super dispose: &rest)
	)
	
	(method (add param1 &tmp temp0 temp1)
		(super add: param1 &rest)
		(= temp0 0)
		(while (< temp0 argc)
			(if ((= temp1 [param1 temp0]) respondsTo: #client)
				(temp1 client: self)
			)
			(++ temp0)
		)
	)
	
	(method (dispatchEvent event &tmp temp0 temp1)
		(= gameTime (+ tickOffset (GetTime)))
		(self animateOnce:)
		(= eventX (event x?))
		(= eventY (event y?))
		(if (== (event type?) nullEvt)
			(= temp0 0)
			(= temp1 (self firstTrue: #onMe event))
			(event dispose:)
			(if (and temp1 (!= temp1 highlightedIcon))
				(= oldMouseY 0)
				(self highlight: temp1)
			)
		else
			(= temp0 (super dispatchEvent: event &rest))
		)
		(return temp0)
	)
	
	(method (noClickHelp)
		(super noClickHelp: &rest)
		(theIconBar
			select: (theIconBar at: 1)
			curIcon: (theIconBar at: 1)
		)
		(theGame setCursor: 8)
	)
	
	(method (buyClue)
		(return
			(cond 
				((and hintFlag (Btst hintFlag)) 1)
				((not numCoins) (HighPrint 15 0) 0)
				(else
					(if hintFlag (Bset hintFlag))
					(if (not (-- numCoins))
						(ego put: 0)
						(HighPrint 15 1)
					else
						(proc5_14
							15
							2
							numCoins
							(if (== numCoins 1) {_} else {s_})
						)
					)
					1
				)
			)
		)
	)
	
	(method (showHelp)
	)
	
	(method (animateOnce)
		(return 0)
	)
	
	(method (goAway)
		(= state (& state $ffdf))
	)
	
	(method (isActive)
		(return (& state $0020))
	)
	
	(method (solvePuzzle param1)
		(SolvePuzzle
			solvedFlag
			(if argc param1 else arcadeLevel)
		)
	)
	
	(method (setScript newScript)
		(if script (script dispose:))
		(if newScript (newScript init: self &rest))
	)
)

(instance puzzleCoin of PuzzleIcon
	(properties
		view 947
		loop 10
		cel 0
		nsLeft 7
		nsTop 2
		signal $0181
		lowlightColor 19
	)
	
	(method (select &tmp temp0)
		(= temp0 (GetPort))
		(if (super select: &rest) (client buyClue:))
		(SetPort temp0)
	)
)

(instance puzzleOK of PuzzleIcon
	(properties
		view 947
		loop 9
		cel 0
		nsLeft 30
		nsTop 2
		signal $00c1
		helpStr {Click here to put this puzzle aside.}
		lowlightColor 19
	)
	
	(method (select)
		(if (super select: &rest) (client goAway:))
	)
)

(instance puzzleHelp of PuzzleIcon
	(properties
		view 947
		loop 7
		cel 0
		nsLeft 70
		nsTop 2
		cursor 70
		message 6
		signal $0181
		lowlightColor 19
	)
	
	(method (show &tmp clientWindow temp1 clientWindowRight temp3 temp4 temp5)
		(super show: &rest)
		(= temp4 (GetPort))
		(SetPort 0)
		(= temp1 ((= clientWindow (client window?)) left?))
		(= clientWindowRight (clientWindow right?))
		(= temp5 (+ (clientWindow top?) (client iconHeight?)))
		(= temp3
			(- (clientWindow bottom?) (client bottomHeight?))
		)
		(Graph
			GDrawLine
			(- temp5 3)
			temp1
			(- temp5 3)
			clientWindowRight
			0
			-1
			-1
		)
		(Graph
			GDrawLine
			(- temp5 2)
			temp1
			(- temp5 2)
			clientWindowRight
			0
			-1
			-1
		)
		(Graph
			GShowBits
			(- temp5 3)
			temp1
			(- temp5 1)
			clientWindowRight
			1
		)
		(Graph
			GDrawLine
			(- temp3 2)
			temp1
			(- temp3 2)
			clientWindowRight
			0
			-1
			-1
		)
		(Graph
			GDrawLine
			(- temp3 1)
			temp1
			(- temp3 1)
			clientWindowRight
			0
			-1
			-1
		)
		(Graph
			GShowBits
			(- temp3 2)
			temp1
			temp3
			clientWindowRight
			1
		)
		(SetPort temp4)
	)
	
	(method (select &tmp temp0)
		(= temp0 (GetPort))
		(if (super select: &rest) (client showHelp:))
		(SetPort temp0)
	)
)
