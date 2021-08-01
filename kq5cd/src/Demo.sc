;;; Sierra Script 1.0 - (do not remove this comment)
(script# 975)
(include sci.sh)
(use Main)
(use Intrface)
(use Save)
(use Actor)

(public
	DR 0
	FakeInput 1
	FakeDir 2
	FakeKey 3
	FakeMouse 4
)

(procedure (FakeInput param1 &tmp temp0)
	(View
		prev: ((View notFacing:) type: 64 message: param1 showStr:)
	)
)

(procedure (FakeDir param1 param2 param3 param4 &tmp viewNotFacing)
	((= viewNotFacing (View notFacing:))
		type: param1
		x: param2
		y: param3
		modifiers: (if (>= argc 3) param4 else 0)
	)
	(SetCursor
		theCursor
		1
		(viewNotFacing x?)
		(viewNotFacing y?)
	)
	(LocalToGlobal viewNotFacing)
	(View prev: viewNotFacing)
)

(procedure (FakeKey)
	(DisposeScript)
	(super quitGame:)
)

(procedure (FakeMouse)
)

(instance DR of SRDialog
	(properties)
	
	(method (flags)
		(super flags:)
		(SetCursor theCursor 1 320 200)
		(self register: (ScriptID (+ curRoomNum global55)))
		(Dialog time: global60)
	)
	
	(method (doit)
		((View notFacing:)
			type: 0
			message: 0
			modifiers: 0
			y: 0
			x: 0
			claimed: 0
		)
		(super doit:)
	)
	
	(method (quitGame)
		(Dialog time: 0)
		(elements quitGame:)
		(DisposeScript (+ curRoomNum global55))
		(super quitGame:)
	)
)
