;;; Sierra Script 1.0 - (do not remove this comment)
(script# PROCS)
(include game.sh)
(use Main)
(use Print)
(use System)

(public
	Btst 0
	Bset 1
	Bclr 2
	Face 3
	proc11_4 4
	EgoDead 5
)

(local
	curRoomPicture
)
(procedure (Btst flagEnum param2 &tmp oldState)
	(= oldState
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
	(return
		(if (not param2)
			(return oldState)
		else
			(return oldState)
		)
	)
)

(procedure (Bset flagEnum &tmp oldState)
	(= oldState (Btst flagEnum 1))
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
	(return oldState)
)

(procedure (Bclr flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
	(return oldState)
)

(procedure (Face actor1 actor2 both whoToCue &tmp temp0 theX theY obj)
	(= obj 0)
	(if (not (> argc 3))
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3) (= obj both))
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= obj whoToCue))
	)
	(= temp0
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1 setHeading: temp0 obj)
)

(procedure (proc11_4 &tmp newPrint newPrintInit)
	((= newPrint (Print new:)) x: 85 y: 32 margin: 0)
	((newPrint addIcon: 952 0 0 0 0) state: 0)
	((newPrint addIcon: 952 1 0 6 100) value: 1 state: 3)
	((newPrint addIcon: 952 2 0 101 100) value: 0 state: 3)
	((newPrint dialog?) mouseHiliting: TRUE)
	(= newPrintInit (newPrint init:))
)

(procedure (EgoDead reason roomNum &tmp temp0)
	(theGame setScript: doTheDeath roomNum reason)
)

(instance doTheDeath of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #hide)
				(= curRoomPicture (curRoom picture?))
				(curRoom drawPic: 999 SHOW_FADE_OUT)
				(= ticks 30)
			)
			(1
				(switch register
					(1
						(messager say: ALL ALL 1 0 self 30)
					)
					(2
						(messager say: ALL ALL 4 0 self 30)
					)
					(3
						(messager say: ALL ALL 2 0 self 30)
					)
					(4
						(messager say: ALL ALL 3 0 self 30)
					)
					(5
						(messager say: ALL ALL 6 0 self 30)
					)
					(6
						(messager say: ALL ALL 5 0 self 30)
					)
					(7
						(messager say: ALL ALL 49 0 self 30)
					)
					(8
						(messager say: ALL ALL 38 0 self 30)
					)
					(9
						(messager say: ALL ALL 37 0 self 30)
					)
					(10
						(messager say: ALL ALL 62 0 self 30)
					)
					(11
						(messager say: ALL ALL 36 0 self 30)
					)
					(12
						(messager say: ALL ALL 61 0 self 30)
					)
					(13
						(messager say: ALL ALL 28 0 self 30)
					)
					(0
						(self cue:)
					)
					(else 
						(Prints
							{You must provide a valid reason when calling EgoDead.}
						)
						(self cue:)
					)
				)
			)
			(2
				(= ticks 30)
			)
			(3
				(if
					(Print
						addText: {You have died, do you want to live?}
						addButton: 1 {YES} 5 15
						addButton: 0 {NO} 30 15
						init:
					)
					(self cue:)
				else
					(= quit TRUE)
				)
			)
			(4
				(Bset 5)
				(if (!= caller 0) (caller cue: register))
				(cast eachElementDo: #show)
				(curRoom drawPic: curRoomPicture SHOW_FADE_IN)
				(self dispose:)
			)
		)
	)
)
