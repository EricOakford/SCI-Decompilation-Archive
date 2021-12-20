;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64010)
(include sci.sh)
(use Main)
(use DialogPlane)
(use NewUser)
(use GenDialog)
(use String)
(use System)

(public
	oMainKeyHandler 0
	oRightClickHandler 1
)

(local
	local0
	[local1 8] = [3 6 0 4 2 5 1 7]
)
(instance oMainKeyHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp theGameNGameSpeed)
		(if (not (& (event type?) $000c))
			(MonoOut
				{Attempt to execute key handler with non-key event. DJM keys.sc}
			)
			(return)
		)
		(if (not (& (event type?) evKEYBOARD)) (return))
		(event claimed: 1)
		(switch (event message?)
			(KEY_TAB
				(if (and (user canControl:) (thePlane isEnabled:))
					(if (== ((ScriptID 64037 2) priority?) 450)
						((ScriptID 64037 2) setPri: -1)
					else
						((ScriptID 64037 2) setPri: 450)
					)
					(UpdatePlane (ScriptID 64037 2))
				)
			)
			(KEY_EXECUTE
				(= theGameNGameSpeed (theGame nGameSpeed?))
				(= theGameNGameSpeed (Max 1 (-- theGameNGameSpeed)))
				(theGame nGameSpeed: theGameNGameSpeed)
				(ego setSpeed: theGameNGameSpeed)
			)
			(KEY_SUBTRACT
				(= theGameNGameSpeed (theGame nGameSpeed?))
				(= theGameNGameSpeed (Min 10 (++ theGameNGameSpeed)))
				(theGame nGameSpeed: theGameNGameSpeed)
				(ego setSpeed: theGameNGameSpeed)
			)
			(61
				(= theGameNGameSpeed 6)
				(ego setSpeed: theGameNGameSpeed)
				(theGame nGameSpeed: theGameNGameSpeed)
			)
			(KEY_SHIFT
				(if (= local0 (not local0))
					(proc64033_0
						(MakeMessageText 0 0 28 1 14)
						(Str with: global288)
					)
					(PalCycle 0 65 165 1 10)
				else
					(proc64033_0
						(MakeMessageText 0 0 27 1 14)
						(Str with: global288)
					)
					(PalCycle 4)
				)
			)
			(KEY_NUMPAD4
				(if (& (event modifiers?) emSHIFT)
					(= mouseX (- mouseX 7))
				else
					(= mouseX (- mouseX 30))
				)
				(event x: mouseX y: mouseY type: 2 claimed: 0)
				(theCursor posn: mouseX mouseY)
			)
			(KEY_RIGHT
				(if (& (event modifiers?) emSHIFT)
					(= mouseX (+ mouseX 7))
				else
					(= mouseX (+ mouseX 30))
				)
				(event x: mouseX y: mouseY type: 2 claimed: 0)
				(theCursor posn: mouseX mouseY)
			)
			(KEY_UP
				(if (& (event modifiers?) emSHIFT)
					(= mouseY (- mouseY 7))
				else
					(= mouseY (- mouseY 30))
				)
				(event x: mouseX y: mouseY type: 2 claimed: 0)
				(theCursor posn: mouseX mouseY)
			)
			(KEY_NUMPAD2
				(if (& (event modifiers?) emSHIFT)
					(= mouseY (+ mouseY 7))
				else
					(= mouseY (+ mouseY 30))
				)
				(event x: mouseX y: mouseY type: 2 claimed: 0)
				(theCursor posn: mouseX mouseY)
			)
			(KEY_SPACE
				(event type: 1 claimed: 0)
			)
			(KEY_RETURN
				(event type: 1 claimed: 0)
			)
			(else  (event claimed: 0))
		)
		(event claimed?)
	)
)

(instance oRightClickHandler of EventCode
	(properties)
	
	(method (handleEvent &tmp temp0)
		(return 0)
	)
)
