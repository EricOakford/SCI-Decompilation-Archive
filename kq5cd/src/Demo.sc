;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEMO)
(include game.sh)
(use Main)
(use Intrface)
(use Save)
(use Actor)

;NOTE: This module has been commented out,
; as it is a remnant from SCI0

;;;(public
;;;	DR 0
;;;	proc975_1 1
;;;	proc975_2 2
;;;	proc975_3 3
;;;	proc975_4 4
;;;)
;;;
;;;(procedure (proc975_1 param1 &tmp temp0)
;;;	(View
;;;		prev: ((View lookStr?) type: 64 message: param1 showStr:)
;;;	)
;;;)
;;;
;;;(procedure (proc975_2 param1 param2 param3 param4 &tmp viewLookStr)
;;;	((= viewLookStr (View lookStr?))
;;;		type: param1
;;;		x: param2
;;;		y: param3
;;;		modifiers: (if (>= argc 3) param4 else 0)
;;;	)
;;;	(SetCursor
;;;		theCursor
;;;		1
;;;		(viewLookStr x?)
;;;		(viewLookStr y?)
;;;	)
;;;	(LocalToGlobal viewLookStr)
;;;	(View prev: viewLookStr)
;;;)
;;;
;;;(procedure (proc975_3)
;;;	(DisposeScript)
;;;	(super quitGame:)
;;;)
;;;
;;;(procedure (proc975_4)
;;;)
;;;
;;;(instance DR of SRDialog
;;;	(properties)
;;;	
;;;	(method (flags)
;;;		(super flags:)
;;;		(SetCursor theCursor 1 320 200)
;;;		(self register: (ScriptID (+ curRoomNum global55)))
;;;		(Dialog time: global60)
;;;	)
;;;	
;;;	(method (doit)
;;;		((View lookStr?)
;;;			type: 0
;;;			message: 0
;;;			modifiers: 0
;;;			y: 0
;;;			x: 0
;;;			claimed: 0
;;;		)
;;;		(super doit:)
;;;	)
;;;	
;;;	(method (quitGame)
;;;		(Dialog time: 0)
;;;		(elements quitGame:)
;;;		(DisposeScript (+ curRoomNum global55))
;;;		(super quitGame:)
;;;	)
;;;)
;;;