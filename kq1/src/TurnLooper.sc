;;; Sierra Script 1.0 - (do not remove this comment)
(script# 850)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use System)


(procedure (localproc_0004 param1 param2 &tmp temp0)
	(cond 
		((> (= temp0 (+ param1 param2)) 7) (= temp0 0))
		((< temp0 0) (= temp0 7))
	)
	(return temp0)
)

(procedure (localproc_002a param1)
	(switch param1
		(1 4)
		(3 6)
		(else  param1)
	)
)

(class TurnLooper of Code
	(properties
		client 0
		inited 0
		turningDir 0
		oldCycler 0
		viewNormal 0
		viewChange 0
		finalLoop 0
		currentLoop 0
		TLHandsOff 0
		TLUserInput 0
		TLUserControl 0
	)
	
	(method (doit theClient param2 &tmp temp0)
		(if (not inited)
			(= inited 1)
			(= client theClient)
			(= viewNormal (client view?))
		)
		(if (== turningDir 0)
			(= currentLoop (localproc_002a (client loop?)))
			(DirLoop client param2)
			(= finalLoop (localproc_002a (client loop?)))
			(if (!= currentLoop finalLoop)
				(= turningDir
					(if
						(or
							(== (= temp0 (- finalLoop currentLoop)) 2)
							(== temp0 -6)
						)
						1
					else
						-1
					)
				)
				(= oldCycler (client cycler?))
				(client view: viewChange cycler: 0 moveSpeed: 200 cel: 0)
				(if (== client ego)
					(= TLHandsOff isHandsOff)
					(= TLUserControl (User controls?))
					(= TLUserInput (User canInput:))
					(= isHandsOff 1)
					(User canInput: 0 controls: 0)
				)
				(self cue:)
			)
		)
	)
	
	(method (dispose)
		(if client
			(client view: viewNormal moveSpeed: 0 setCycle: 0)
		)
		(if oldCycler (oldCycler dispose:))
		(super dispose:)
	)
	
	(method (cue &tmp temp0)
		(if (== currentLoop finalLoop)
			(client
				view: viewNormal
				cycler: oldCycler
				moveSpeed: 0
				loop:
				(switch finalLoop
					(0 0)
					(2 2)
					(4 1)
					(6 3)
				)
			)
			(= turningDir (= oldCycler 0))
			(if (== client ego)
				(User controls: TLUserControl)
				(User canInput: TLUserInput)
				(= isHandsOff TLHandsOff)
			)
		else
			(= currentLoop (localproc_0004 currentLoop turningDir))
			(if (mod currentLoop 2)
				(= temp0 (/ currentLoop 2))
				(client loop: temp0)
				(if
					(or
						(and (== turningDir 1) (== (mod temp0 2) 0))
						(and (== turningDir -1) (mod temp0 2))
					)
					(client setCel: 0 setCycle: EndLoop self)
				else
					(client setCel: 255 setCycle: BegLoop self)
				)
			else
				(self cue:)
			)
		)
	)
)
