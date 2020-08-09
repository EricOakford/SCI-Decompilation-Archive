;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPEGO)
(include game.sh) (include "19.shm")
(use Main)
(use StopWalk)
(use Window)
(use Ego)
(use Sound)
(use Motion)
(use System)


(local
	[str 120]
	local120 =  3
	local121 =  3
	oldSpeed
)
(class FPEgo of Ego
	(properties
		name {ego}
		noun N_EGO
		modNum FPEGO
		sightAngle 180
		state startUpdOn
		view 800
		wearingGuns 0
	)
	
	(method (get what theObj param3 param4 &tmp temp0)
		(if (== what -1)
			((inventory at: [what 1]) moveTo: self)
			((inventory at: [what 1])
				realOwner: ((inventory at: [what 1]) owner?)
				owner: 0
			)
		else
			(super get: what &rest)
			((inventory at: what)
				realOwner: ((inventory at: what) owner?)
				owner: 0
			)
			(messager say: (theObj noun?) V_DO NULL 0 0 curRoomNum)
		)
		(if (and (>= argc 3) param3)
			(if (and (== argc 4) param4) (= global140 param4))
			(ego setScript: sReach 0 param3)
		)
	)
	
	(method (put what &tmp theItem)
		(= theItem 0)
		(while (< theItem argc)
			((inventory at: [what theItem])
				owner: ((inventory at: [what theItem]) realOwner?)
			)
			((inventory at: [what theItem]) realOwner: 0)
			(++ theItem)
		)
		(super put: what &rest)
		(cond 
			((not (user canControl:))
				(if (== oldCurIcon (theIconBar at: 5))
					(= oldCurIcon (theIconBar at: 0))
				)
				(theGame setCursor: waitCursor)
			)
			((== (inventory state?) 2080) (theGame setCursor: 999))
			(else
				(theIconBar curIcon: (theIconBar at: 0))
				(theGame setCursor: ((theIconBar at: 0) cursor?))
			)
		)
	)
	
	(method (has what)
		(return
			(if (== ((inventory at: what) realOwner?) self)
			else
				(== ((inventory at: what) owner?) self)
			)
		)
	)
	
	(method (normalize theView)
		(= local120 3)
		(= local121 3)
		(= view
			(cond 
				(argc theView)
				((OneOf curRoomNum 730 740) 845)
				(wearingGuns 842)
				(else 800)
			)
		)
		(self
			setLoop: -1
			setCel: -1
			setPri: -1
			setMotion: 0
			setCycle: StopWalk -1
			setStep: 6 4
			z: 0
			illegalBits: cWHITE
			ignoreActors: 0
		)
	)
	
	(method (showInv &tmp saveWindow saveCursor temp2)
		(if (inventory firstTrue: #ownedBy ego)
			(= saveCursor normalCursor)
			(= normalCursor ARROW_CURSOR)
			(= saveWindow systemWindow)
			(= systemWindow SysWindow)
			(inventory showSelf: ego)
			(= normalCursor saveCursor)
			(if
			(== (= temp2 ((theIconBar curIcon?) cursor?)) ARROW_CURSOR)
				(theGame setCursor: waitCursor)
			else
				(theGame setCursor: temp2)
			)
			(if (not (theIconBar curInvIcon?))
				(theIconBar disable: ICON_USEIT)
				(if (& ((theIconBar curIcon?) signal?) DISABLED)
					(theIconBar advanceCurIcon:)
				)
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
			(= systemWindow saveWindow)
			(narrator showTitle: FALSE name: {Narrator})
		else
			(messager say: 988 NULL C_EMPTY 0 0 FPEGO)
		)
	)
)

(instance sReach of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= oldSpeed (ego moveSpeed?))
				(ego setSpeed: 6)
				(= cycles 3)
			)
			(1
				(if (ego wearingGuns?)
					(ego view: 125)
				else
					(ego view: 124)
				)
				(switch register
					(3
						(if (> (ego heading?) 180)
							(ego setLoop: 1)
						else
							(ego setLoop: 0)
						)
					)
					(2
						(if (> (ego heading?) 180)
							(ego setLoop: 3)
						else
							(ego setLoop: 2)
						)
					)
					(1
						(if (> (ego heading?) 180)
							(ego setLoop: 5)
						else
							(ego setLoop: 4)
						)
					)
				)
				(ego
					cel: 0
					setCycle: CycleTo (if (== register 3) 3 else 6) 1 self
				)
			)
			(2
				(if (IsObject global140) (global140 dispose:))
				(ego setCycle: EndLoop self)
			)
			(3
				(ego
					normalize:
					setHeading: (if (OneOf (ego loop?) 1 3 5) 270 else 90)
				)
				(= global140 0)
				(ego setSpeed: oldSpeed)
				(UnLoad RES_VIEW 124)
				(UnLoad RES_VIEW 125)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giggleSound of Sound
	(properties
		flags mNOPAUSE
		number 2140
	)
)